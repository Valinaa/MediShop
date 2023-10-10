package tech.valinaa.medishop.auth.user.pojo.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Valinaa
 * @Date 2023/10/2 12:23
 * @Description 用户类型枚举
 */
@Schema(enumAsRef = true, name = "MedicineStatusEnum", title = "药品状态枚举",
        description = "0: guest 游客;   1: customer 消费者;   "
                + "2: business 卖家;   3: admin 管理员   ",
        allowableValues = {"0", "1", "2", "3"})
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
    @NotBlank
    private final String code;
    private final String desc;
}
