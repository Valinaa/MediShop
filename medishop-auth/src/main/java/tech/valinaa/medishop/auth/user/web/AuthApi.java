package tech.valinaa.medishop.auth.user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.auth.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.user.pojo.UserResponse;

import java.util.Map;

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
     * @param userRequest 用户请求实体
     * @return token
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    Result<Map<String, String>> login(@RequestBody @Valid UserRequest userRequest);
    
    /**
     * 用户注册Api
     *
     * @param userRequest 用户请求实体
     * @return 用户信息
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册")
    Result<UserResponse> register(@RequestBody @Valid UserRequest userRequest);
    
    /**
     * 用户注销Api
     *
     * @param userRequest 用户请求实体
     * @return 注销是否成功
     */
    @PostMapping("/logout")
    @Operation(summary = "用户登出")
    Result<Boolean> logout(@RequestBody @Valid UserRequest userRequest);
}
