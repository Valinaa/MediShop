package tech.valinaa.medishop.auth.security.user;

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
    
    GUEST(List.of("READ_RAW")),
    CUSTOMER(List.of("READ_GOODS", "BUY_GOODS", "READ_RANK")),
    BUSINESS(List.of("READ_GOODS", "MANAGE_GOODS", "READ_RANK")),
    ADMIN(List.of("READ_GOODS", "READ_RANK", "BUY_GOODS",
            "MANAGE_USER", "MANAGE_GOODS", "MANAGE_RANK"));
    
    @EnumValue
    private final List<String> authorities;
}
