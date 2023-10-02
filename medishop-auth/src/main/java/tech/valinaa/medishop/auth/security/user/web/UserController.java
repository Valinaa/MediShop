package tech.valinaa.medishop.auth.security.user.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.auth.security.user.UserService;
import tech.valinaa.medishop.auth.security.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.security.user.pojo.UserResponse;
import tech.valinaa.medishop.auth.util.JwtUtil;
import tech.valinaa.medishop.core.model.enums.ResultCodeEnum;

import java.util.HashMap;
import java.util.Map;

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
        var authentication = new UsernamePasswordAuthenticationToken(
                userRequest.getUsername(), userRequest.getPassword()
        );
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(authentication);
            log.info("authenticate:{}", authenticate);
        } catch (Exception e) {
            log.error("login error:{}", e.getMessage());
        }
        if (authenticate == null) {
            log.error("login error: authenticate is null");
            return Result.failure(ResultCodeEnum.LOGIN_ERROR);
        }
        UserDetails userDetails = userService.loadUserByUsername(userRequest.getUsername());
        String token = JwtUtil.createToken(userDetails);
        return Result.success(new HashMap<>() {{
            put("token", token);
        }});
    }
    
    @Override
    public Result<UserResponse> register(UserRequest userRequest) {
        return userService.register(userRequest);
    }
}
