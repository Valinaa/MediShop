package tech.valinaa.medishop.auth.user.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.authorization.AdminOnly;
import tech.valinaa.medishop.api.authorization.LoginRequired;
import tech.valinaa.medishop.api.page.BasePageRequest;
import tech.valinaa.medishop.api.page.PageResult;
import tech.valinaa.medishop.auth.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.user.pojo.UserResponse;

/**
 * @author Valinaa
 * @Date 2023/10/2 21:20
 * @Description 用户相关接口
 */
@Tag(name = "用户管理Api", description = "用户相关Api")
@RequestMapping("/api/v1/user")
@LoginRequired
public interface UserApi {
    
    /**
     * 获取单个用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @GetMapping("/{id}")
    @Operation(summary = "获取单个用户信息")
    Result<UserResponse> getOne(@PathVariable Long id);
    
    /**
     * 更新用户信息
     *
     * @param userRequest 用户请求实体
     * @return 用户信息
     */
    @PutMapping
    @Operation(summary = "更新用户信息")
    Result<UserResponse> update(@RequestBody @Validated UserRequest userRequest);
    
    /**
     * 删除用户信息
     *
     * @param id 用户id
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户信息")
    Result<Boolean> delete(@PathVariable Long id);
    
    /**
     * 获取所有用户信息
     *
     * @param pageRequest 分页请求实体
     * @return 用户信息列表
     */
    @GetMapping("/list")
    @Operation(summary = "获取所有用户信息")
    @AdminOnly
    Result<PageResult<UserResponse>> getPageList(BasePageRequest pageRequest);
}
