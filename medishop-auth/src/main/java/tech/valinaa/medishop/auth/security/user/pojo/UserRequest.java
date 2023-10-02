package tech.valinaa.medishop.auth.security.user.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tech.valinaa.medishop.auth.security.user.UserTypeEnum;

import java.util.List;

/**
 * @author Valinaa
 * @Date 2023/10/2 19:48
 * @Description 用户请求实体
 */
@Data
@Schema(title = "用户请求实体")
public class UserRequest {
    @NotBlank
    @Schema(title = "username", description = "用户名", type = "string", requiredMode = Schema.RequiredMode.REQUIRED)
    private String username;
    @NotBlank
    @Schema(title = "password", description = "密码", type = "string", requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;
    @Schema(title = "authorities", description = "权限", type = "array", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private List<SimpleGrantedAuthority> authorities;
    @NotBlank
    @Schema(title = "fullName", description = "全名", type = "string", requiredMode = Schema.RequiredMode.REQUIRED)
    private String fullName;
    @NotBlank
    @Schema(title = "email", description = "邮箱", type = "string", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    @Schema(title = "phoneNumber", description = "电话号码", type = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String phoneNumber;
    @Schema(title = "address", description = "地址", type = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String address;
    @Schema(title = "ipAddress", description = "IP地址", type = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String ipAddress;
    @NotBlank
    @Schema(title = "userType", description = "用户类型", type = "string", requiredMode = Schema.RequiredMode.REQUIRED)
    private UserTypeEnum userType;
    @Schema(title = "licenseImageUrl", description = "证件照", type = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String licenseImageUrl;
}
