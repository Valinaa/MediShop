package tech.valinaa.medishop.core.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import tech.valinaa.medishop.api.enums.AuthorityEnum;
import tech.valinaa.medishop.api.enums.UserTypeEnum;
import tech.valinaa.medishop.core.mybatis.List2StringTypeHandler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Valinaa
 * @Date 2023/10/2 12:01
 * @Description Spring Security 用户
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user", autoResultMap = true)
public class UserDO extends BaseDO implements UserDetails, Serializable {
    
    private String username;
    private String password;
    @TableField(typeHandler = List2StringTypeHandler.class)
    private List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String ipAddress;
    private String ipRegion;
    private UserTypeEnum userType;
    private String licenseImageUrl;
    
    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return this.authorities;
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
    
    /**
     * 设置权限
     *
     * @param authorityEnum {@link AuthorityEnum}
     * @see AuthorityEnum
     */
    public void setAuthorities(AuthorityEnum authorityEnum) {
        this.setAuthorities(authorityEnum.getAuthorities().toArray(new String[0]));
    }
    
    /**
     * 设置权限
     *
     * @param authorities 权限名集合
     */
    public void setAuthorities(List<String> authorities) {
        this.setAuthorities(authorities.toArray(new String[0]));
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
