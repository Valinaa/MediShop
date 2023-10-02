package tech.valinaa.medishop.auth.security.user.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.valinaa.medishop.auth.security.user.AuthorityEnum;
import tech.valinaa.medishop.auth.security.user.UserTypeEnum;
import tech.valinaa.medishop.core.model.dataobject.BaseDO;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author Valinaa
 * @Date 2023/10/2 12:01
 * @Description Spring Security 用户
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("user")
public class UserDO extends BaseDO implements UserDetails, Serializable {
    
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
    
    /**
     * 设置权限
     *
     * @param authorityEnum {@link AuthorityEnum}
     * @see AuthorityEnum
     */
    public void setAuthorities(AuthorityEnum authorityEnum) {
        authorityEnum.getAuthorities().forEach(
                i -> this.authorities.add(new SimpleGrantedAuthority(i))
        );
    }
    
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
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }
    
    @Override
    public String getPassword() {
        return this.password;
    }
    
    @Override
    public String getUsername() {
        return this.username;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return this.getDeleted() == 0;
    }
}
