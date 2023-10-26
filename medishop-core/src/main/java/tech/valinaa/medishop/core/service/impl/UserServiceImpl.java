package tech.valinaa.medishop.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.authorization.response.JWTResponse;
import tech.valinaa.medishop.api.enums.AuthorityEnum;
import tech.valinaa.medishop.api.enums.ResultCodeEnum;
import tech.valinaa.medishop.api.user.request.UserRequest;
import tech.valinaa.medishop.api.user.response.UserResponse;
import tech.valinaa.medishop.core.converter.UserConverter;
import tech.valinaa.medishop.core.dao.UserMapper;
import tech.valinaa.medishop.core.model.UserDO;
import tech.valinaa.medishop.core.service.UserService;
import tech.valinaa.medishop.utils.Constants;
import tech.valinaa.medishop.utils.JacksonUtil;
import tech.valinaa.medishop.utils.JwtUtil;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Pattern;

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
    
    @Lazy
    @Resource
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public Result<JWTResponse> login(String username, String password) {
        var jwtRes = new JWTResponse();
        var authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            Optional.ofNullable(authenticationManager.authenticate(authentication))
                    .ifPresentOrElse(
                            auth -> {
                                log.info("authenticate: {}", auth);
                                SecurityContextHolder.getContext().setAuthentication(auth);
                                jwtRes.setAccessToken(JwtUtil.createAccessToken((UserDetails) auth.getPrincipal()));
                                jwtRes.setRefreshToken(JwtUtil.createRefreshToken((UserDetails) auth.getPrincipal()));
                                jwtRes.setExpiresIn(LocalDateTime.now().plusSeconds(Constants.ACCESS_TOKEN_EXPIRATION_TIME));
                            },
                            () -> log.error("login error: authenticate is null")
                    );
        } catch (AuthenticationException e) {
            log.error("login error:{}", e.getMessage());
        }
        return jwtRes.getAccessToken().isBlank()
                ? Result.failure(ResultCodeEnum.LOGIN_ERROR)
                : Result.success(jwtRes);
    }
    
    @Override
    @SuppressWarnings("checkstyle:MagicNumber")
    public Result<UserResponse> register(UserRequest userRequest) {
        var attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        Optional.ofNullable(attributes)
                .ifPresentOrElse(
                        attribute -> {
                            var ipAddr = attribute.getRequest().getRemoteAddr();
                            userRequest.setIpAddress(ipAddr);
                            var uri = URI.create("https://ip.useragentinfo.com/ipv6/" + ipAddr);
                            final var ipv4Pattern = Pattern.compile("^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
                                    + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
                                    + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\."
                                    + "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
                            var isIpv4 = ipv4Pattern.matcher(ipAddr).matches();
                            if (isIpv4) {
                                uri = URI.create("https://ip.useragentinfo.com/jsonp?ip=" + ipAddr);
                            }
                            try {
                                var httpRes = HttpClient.newHttpClient().send(
                                                HttpRequest.newBuilder().uri(uri).build(),
                                                HttpResponse.BodyHandlers.ofString())
                                        .body();
                                var res = isIpv4 ? httpRes.substring(9, httpRes.length() - 2) : httpRes;
                                var json = JacksonUtil.parseJSONObject(res);
                                var ipRegin = JacksonUtil.getString(json, "country");
                                if (isIpv4) {
                                    ipRegin += " " + JacksonUtil.getString(json, "province")
                                            + " " + JacksonUtil.getString(json, "city")
                                            + " " + JacksonUtil.getString(json, "area");
                                } else {
                                    ipRegin += " " + JacksonUtil.getString(json, "region")
                                            + " " + JacksonUtil.getString(json, "city");
                                }
                                userRequest.setIpRegion(ipRegin.strip());
                            } catch (IOException | InterruptedException e) {
                                log.error("Cannot get ip address: {}", e.getMessage());
                            }
                        },
                        () -> log.warn("Cannot get ip address ———— Request attributes is null!"));
        var username = userRequest.getUsername();
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        var userRes = UserConverter.INSTANCE.req2Response(userRequest);
        if (this.lambdaQuery().eq(UserDO::getUsername, username).one() != null) {
            return Result.failure(userRes, ResultCodeEnum.DUPLICATE_USERNAME);
        }
        if (!this.save(UserConverter.INSTANCE.req2DO(userRequest))) {
            return Result.failure(userRes, ResultCodeEnum.REGISTER_FAILED);
        }
        return Result.success(userRes);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        var user = Optional.of(this.lambdaQuery().eq(UserDO::getUsername, username).oneOpt())
                .orElseThrow(() -> new UsernameNotFoundException("Error when search username from database!"))
                .orElseThrow(() -> new UsernameNotFoundException("不存在username为" + username + "的用户!"));
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
