package tech.valinaa.medishop.auth.user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.auth.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.user.pojo.UserResponse;

import java.util.Map;

/**
 * @author Valinaa
 * @Date 2023/10/2 21:20
 * @Description 用户相关接口
 */
@Tag(name = "用户管理Api", description = "用户相关Api")
@RequestMapping("/user")
public interface UserApi {
    
    /**
     * 用户登录Api
     *
     * @param userRequest 用户请求实体
     * @return token
     */
    @PostMapping("/login")
    @Operation(summary = "用户登录")
    Result<Map<String, String>> login(@RequestBody UserRequest userRequest);
    
    /**
     * 用户注册Api
     *
     * @param userRequest 用户请求实体
     * @return 用户信息
     */
    @PostMapping("/register")
    @Operation(summary = "用户注册")
    Result<UserResponse> register(@RequestBody UserRequest userRequest);
    
    /**
     * 用户注销Api
     *
     * @param userRequest 用户请求实体
     * @return 注销是否成功
     */
    @PostMapping("/logout")
    @Operation(summary = "用户注销")
    boolean logout(@RequestBody UserRequest userRequest);
    
    /**
     * 获取单个用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取单个用户信息")
    Result<UserResponse> getOne(@PathVariable Long id);
}
