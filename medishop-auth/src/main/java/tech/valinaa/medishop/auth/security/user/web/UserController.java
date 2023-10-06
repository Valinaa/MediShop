package tech.valinaa.medishop.auth.security.user.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.auth.security.user.UserService;
import tech.valinaa.medishop.auth.security.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.security.user.pojo.UserResponse;

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
    
    @Override
    public Result<Map<String, String>> login(UserRequest userRequest) {
        return userService.login(userRequest);
    }
    
    @Override
    public Result<UserResponse> register(UserRequest userRequest) {
        return userService.register(userRequest);
    }
    
    @Override
    public boolean logout(UserRequest userRequest) {
        return false;
    }
    
    @Override
    public Result<UserResponse> getOne(Long id) {
        return null;
    }
}
