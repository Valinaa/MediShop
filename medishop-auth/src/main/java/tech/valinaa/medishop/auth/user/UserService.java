package tech.valinaa.medishop.auth.user;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import tech.valinaa.medishop.auth.JWTResponse;
import tech.valinaa.medishop.auth.user.pojo.UserDO;
import tech.valinaa.medishop.auth.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.user.pojo.UserResponse;
import tech.valinaa.medishop.core.model.Result;

/**
 * @author Valinaa
 * @Date 2023/10/2 12:36
 */
public interface UserService extends IService<UserDO>, UserDetailsService {
    
    /**
     * 用户登录Api
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    Result<JWTResponse> login(String username, String password);
    
    /**
     * 用户注册
     *
     * @param userRequest 用户请求实体
     * @return 用户信息
     */
    Result<UserResponse> register(UserRequest userRequest);
}
