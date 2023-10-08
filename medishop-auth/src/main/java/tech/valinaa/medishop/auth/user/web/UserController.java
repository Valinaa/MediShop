package tech.valinaa.medishop.auth.user.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.page.BasePageRequest;
import tech.valinaa.medishop.api.page.PageResult;
import tech.valinaa.medishop.auth.user.UserConverter;
import tech.valinaa.medishop.auth.user.UserService;
import tech.valinaa.medishop.auth.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.user.pojo.UserResponse;

/**
 * @author Valinaa
 * @Date 2023/10/2 21:19
 */
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {
    
    private final UserService userService;
    
    @Override
    public Result<UserResponse> getOne(Long id) {
        return Result.convert(UserConverter.INSTANCE.dao2Response(userService.getById(id)));
    }
    
    @Override
    public Result<UserResponse> update(UserRequest userRequest) {
        var userDO = UserConverter.INSTANCE.req2DO(userRequest);
        var res = UserConverter.INSTANCE.dao2Response(userDO);
        return Result.convert(userService.updateById(userDO), res);
    }
    
    @Override
    public Result<Boolean> delete(Long id) {
        return Result.convert(userService.removeById(id));
    }
    
    @Override
    public Result<PageResult<UserResponse>> getPageList(BasePageRequest pageRequest) {
        return null;
    }
}
