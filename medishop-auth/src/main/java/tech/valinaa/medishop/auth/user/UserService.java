package tech.valinaa.medishop.auth.user;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.auth.user.pojo.UserDO;
import tech.valinaa.medishop.auth.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.user.pojo.UserResponse;

import java.util.Map;

/**
 * @author Valinaa
 * @Date 2023/10/2 12:36
 */
public interface UserService extends IService<UserDO>, UserDetailsService {
    
    /**
     * 用户登录Api
     *
     * @param userRequest 用户请求实体
     * @return token
     */
    Result<Map<String, String>> login(@RequestBody @Valid UserRequest userRequest);
    
    /**
     * 用户注册
     *
     * @param userRequest 用户请求实体
     * @return 用户信息
     */
    Result<UserResponse> register(UserRequest userRequest);
}
