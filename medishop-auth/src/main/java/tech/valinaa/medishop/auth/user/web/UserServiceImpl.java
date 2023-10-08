package tech.valinaa.medishop.auth.user.web;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jthinking.common.util.ip.IPInfoUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.auth.user.UserConverter;
import tech.valinaa.medishop.auth.user.UserService;
import tech.valinaa.medishop.auth.user.pojo.UserDO;
import tech.valinaa.medishop.auth.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.user.pojo.UserResponse;
import tech.valinaa.medishop.auth.user.pojo.enums.AuthorityEnum;
import tech.valinaa.medishop.auth.util.JwtUtil;
import tech.valinaa.medishop.core.model.enums.ResultCodeEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Valinaa
 * @Date 2023/10/2 19:42
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    
    @Lazy
    @Resource
    private AuthenticationManager authenticationManager;
    
    @Override
    public Result<Map<String, String>> login(UserRequest userRequest) {
        var map = new HashMap<String, String>();
        var authentication = new UsernamePasswordAuthenticationToken(
                userRequest.getUsername(), userRequest.getPassword()
        );
        try {
            Optional.ofNullable(authenticationManager.authenticate(authentication))
                    .ifPresentOrElse(
                            auth -> {
                                log.info("authenticate: {}", auth);
                                var userDetails = this.loadUserByUsername(userRequest.getUsername());
                                var token = JwtUtil.createToken(userDetails);
                                map.put("token", token);
                            },
                            () -> log.error("login error: authenticate is null")
                    );
        } catch (AuthenticationException e) {
            log.error("login error:{}", e.getMessage());
        }
        return map.isEmpty()
                ? Result.failure(ResultCodeEnum.LOGIN_ERROR)
                : Result.success(map);
    }
    
    @Override
    public Result<UserResponse> register(UserRequest userRequest) {
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Optional.ofNullable(attributes)
                .ifPresentOrElse(
                        attribute -> {
                            var ipAddr = attribute.getRequest().getRemoteAddr();
                            userRequest.setIpAddress(ipAddr);
                            userRequest.setIpRegion(IPInfoUtils.getIpInfo(ipAddr).getAddress());
                        },
                        () -> log.warn("Cannot get ip address ———— Request attributes is null!"));
        var username = userRequest.getUsername();
        var userRes = UserConverter.INSTANCE.req2Response(userRequest);
        if (this.getOne(this.lambdaQuery().eq(UserDO::getUsername, username)) != null) {
            return Result.failure(userRes, ResultCodeEnum.DUPLICATE_USERNAME);
        }
        if (!this.save(UserConverter.INSTANCE.req2DO(userRequest))) {
            return Result.failure(userRes, ResultCodeEnum.REGISTER_FAILED);
        }
        return Result.success(userRes);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = this.getOne(this.lambdaQuery().eq(UserDO::getUsername, username));
        if (user == null) {
            throw new UsernameNotFoundException("不存在username为" + username + "的用户!");
        }
        if (user.getAuthorities().isEmpty()) {
            switch (user.getUserType()) {
                case ADMIN -> user.setAuthorities(AuthorityEnum.ADMIN);
                case BUSINESS -> user.setAuthorities(AuthorityEnum.BUSINESS);
                case CUSTOMER -> user.setAuthorities(AuthorityEnum.CUSTOMER);
                default -> user.setAuthorities(AuthorityEnum.GUEST);
            }
        }
        return user;
    }
}
