package tech.valinaa.medishop.auth.user.pojo;

import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tech.valinaa.medishop.auth.user.pojo.enums.UserTypeEnum;

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
    @Getter
    private List<SimpleGrantedAuthority> authorities;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String ipAddress;
    private String ipRegion;
    private UserTypeEnum userType;
    private String licenseImageUrl;
    
    /**
     * 设置权限
     *
     * @param authorities 若干 权限名
     */
    public void setAuthorities(String... authorities) {
        for (String authority : authorities) {
            this.authorities.add(new SimpleGrantedAuthority(authority));
        }
    }
}
