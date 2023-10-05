package tech.valinaa.medishop.auth.security.user.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.auth.security.user.UserService;
import tech.valinaa.medishop.auth.security.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.security.user.pojo.UserResponse;
import tech.valinaa.medishop.auth.util.JwtUtil;
import tech.valinaa.medishop.core.model.enums.ResultCodeEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Valinaa
 * @Date 2023/10/2 21:19
 * @Description 用户控制器
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    
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
                                var userDetails = userService.loadUserByUsername(userRequest.getUsername());
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
        return userService.register(userRequest);
    }
}
