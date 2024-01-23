package tech.valinaa.medishop.web.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.authorization.AuthApi;
import tech.valinaa.medishop.api.authorization.response.JWTResponse;
import tech.valinaa.medishop.api.user.request.UserRequest;
import tech.valinaa.medishop.api.user.response.UserResponse;
import tech.valinaa.medishop.core.service.UserService;

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
    public Result<JWTResponse> login(@NotBlank String username, @NotBlank String password, String captcha) {
        return userService.login(username, password, captcha);
    }
    
    @Override
    public Result<UserResponse> register(@RequestBody @Validated UserRequest userRequest) {
        return userService.register(userRequest);
    }
    
    @Override
    public Result<Boolean> logout(@RequestBody @Validated UserRequest userRequest) {
        return Result.success(false);
    }
    
    @Override
    public Result<String> getCaptcha(Long timestamp) {
        return userService.getCaptcha(timestamp);
    }
    
    @Override
    public Result<Boolean> verifyRecaptcha(String token) {
        return userService.verifyRecaptcha(token);
    }
    
}
