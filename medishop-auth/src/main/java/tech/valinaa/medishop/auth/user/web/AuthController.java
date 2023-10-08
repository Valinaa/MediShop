package tech.valinaa.medishop.auth.user.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.auth.user.UserService;
import tech.valinaa.medishop.auth.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.user.pojo.UserResponse;

import java.util.Map;

/**
 * @author Valinaa
 * @Date 2023/10/8 13:37
 */

@Validated
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    
    private final UserService userService;
    
    @Override
    public Result<Map<String, String>> login(@RequestBody @Valid UserRequest userRequest) {
        return userService.login(userRequest);
    }
    
    @Override
    public Result<UserResponse> register(@RequestBody @Valid UserRequest userRequest) {
        return userService.register(userRequest);
    }
    
    @Override
    public Result<Boolean> logout(@RequestBody @Valid UserRequest userRequest) {
        return Result.success(false);
    }
    
}
