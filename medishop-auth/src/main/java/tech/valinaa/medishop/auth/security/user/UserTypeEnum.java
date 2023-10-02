package tech.valinaa.medishop.auth.security.user;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Valinaa
 * @Date 2023/10/2 12:23
 * @Description 用户类型枚举
 */
@Getter
@AllArgsConstructor
public enum UserTypeEnum {
    /**
     * 用户类型
     * 0: guest, 1: customer, 2: business, 3: admin
     */
    GUEST("0", "游客"),
    CUSTOMER("1", "消费者"),
    BUSINESS("2", "卖家"),
    ADMIN("3", "管理员");
    
    @EnumValue
    private final String code;
    private final String desc;
}
