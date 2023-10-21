package tech.valinaa.medishop.auth.user.web;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.auth.JWTResponse;
import tech.valinaa.medishop.auth.user.UserService;
import tech.valinaa.medishop.auth.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.user.pojo.UserResponse;
import tech.valinaa.medishop.core.model.Result;

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
    public Result<JWTResponse> login(@NotBlank String username, @NotBlank String password) {
        return userService.login(username, password);
    }
    
    @Override
    public Result<UserResponse> register(@RequestBody @Validated UserRequest userRequest) {
        return userService.register(userRequest);
    }
    
    @Override
    public Result<Boolean> logout(@RequestBody @Validated UserRequest userRequest) {
        return Result.success(false);
    }
    
}
