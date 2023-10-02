package tech.valinaa.medishop.auth.security.user;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.auth.security.user.pojo.UserDO;
import tech.valinaa.medishop.auth.security.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.security.user.pojo.UserResponse;

/**
 * @author Valinaa
 * @Date 2023/10/2 12:36
 * @Description
 */
public interface UserService extends IService<UserDO>, UserDetailsService {
    /**
     * 用户注册
     *
     * @param userRequest 用户请求实体
     * @return 用户信息
     */
    Result<UserResponse> register(UserRequest userRequest);
}
