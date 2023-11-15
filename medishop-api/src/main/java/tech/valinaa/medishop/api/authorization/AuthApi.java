package tech.valinaa.medishop.api.authorization;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.authorization.response.JWTResponse;
import tech.valinaa.medishop.api.user.request.UserRequest;
import tech.valinaa.medishop.api.user.response.UserResponse;

/**
 * @author Valinaa
 * @Date 2023/10/8 8:38
 * @Description 用户操作相关接口
 */
@Tag(name = "用户认证Api", description = "用户认证相关Api")
@RequestMapping("/api/v1")
public interface AuthApi {
    
    /**
     * 用户登录Api
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录成功后返回token")
    Result<JWTResponse> login(@NotBlank String username, @NotBlank String password, @NotBlank String captcha);
    
    /**
     * 用户注册Api
     *
     * @param userRequest 用户请求实体
     * @return 用户信息
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "用户注册成功后返回用户信息")
    Result<UserResponse> register(@RequestBody @Validated UserRequest userRequest);
    
    /**
     * 用户注销Api
     *
     * @param userRequest 用户请求实体
     * @return 注销是否成功
     */
    @PostMapping("/logout")
    @Operation(summary = "用户登出", description = "用户登出成功后返回true")
    Result<Boolean> logout(@RequestBody @Validated UserRequest userRequest);
    
    @GetMapping("/captcha")
    @Operation(summary = "获取验证码", description = "获取验证码")
    Result<String> getCaptcha(Long timestamp);
}
