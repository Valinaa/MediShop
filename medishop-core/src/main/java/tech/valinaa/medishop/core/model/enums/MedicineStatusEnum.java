package tech.valinaa.medishop.core.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author Valinaa
 * @Date 2023/9/28 14:15
 * @Description 药品状态枚举
 */
@Getter
public enum MedicineStatusEnum {
    /**
     * 药品状态
     */
    ON_SALE("1", "在售"),
    OFF_SALE("2", "下架"),
    DELETE("3", "删除");

    @EnumValue
    private final String code;
    private final String desc;

    MedicineStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
