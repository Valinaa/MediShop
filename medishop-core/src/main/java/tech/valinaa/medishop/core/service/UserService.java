package tech.valinaa.medishop.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.authorization.response.JWTResponse;
import tech.valinaa.medishop.api.user.request.UserRequest;
import tech.valinaa.medishop.api.user.response.UserResponse;
import tech.valinaa.medishop.core.model.UserDO;

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
     * @param captcha  验证码
     * @return token
     */
    Result<JWTResponse> login(String username, String password, String captcha);
    
    /**
     * 用户注册
     *
     * @param userRequest 用户请求实体
     * @return 用户信息
     */
    Result<UserResponse> register(UserRequest userRequest);
    
    /**
     * 获取验证码
     *
     * @param timestamp 时间戳
     * @return 验证码
     */
    Result<String> getCaptcha(Long timestamp);
    
    /**
     * 验证Recaptcha
     *
     * @param token token
     * @return 是否通过
     */
    Result<Boolean> verifyRecaptcha(String token);
}
