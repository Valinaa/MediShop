package tech.valinaa.medishop.auth.user.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author Valinaa
 * @Date 2023/10/2 19:58
 * @Description 权限枚举
 */
@Getter
@RequiredArgsConstructor
public enum AuthorityEnum {
    
    GUEST(List.of("ROLE_READ_RAW")),
    CUSTOMER(List.of("ROLE_READ", "ROLE_BUY_GOODS")),
    BUSINESS(List.of("ROLE_READ", "ROLE_MANAGE_GOODS")),
    ADMIN(List.of("ROLE_READ", "ROLE_BUY_GOODS", "ROLE_MANAGE_USER",
            "ROLE_MANAGE_GOODS", "ROLE_MANAGE_RANK"));
    
    @EnumValue
    private final List<String> authorities;
}
