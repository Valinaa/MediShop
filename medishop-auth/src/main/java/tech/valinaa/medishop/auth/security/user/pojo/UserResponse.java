package tech.valinaa.medishop.auth.security.user.pojo;

import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tech.valinaa.medishop.auth.security.user.UserTypeEnum;

import java.util.List;

/**
 * @author Valinaa
 * @Date 2023/10/2 19:44
 * @Description User返回结果模板
 */
@Data
public class UserResponse {
    private String username;
    private String password;
    private List<SimpleGrantedAuthority> authorities;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String ipAddress;
    private UserTypeEnum userType;
    private String licenseImageUrl;
}
