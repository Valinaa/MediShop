package tech.valinaa.medishop.api.user.response;

import lombok.Data;
import tech.valinaa.medishop.core.model.enums.UserTypeEnum;

import java.util.ArrayList;
import java.util.Arrays;
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
    private List<String> authorities = new ArrayList<>();
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
        this.authorities.addAll(Arrays.asList(authorities));
    }
}
