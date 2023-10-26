package tech.valinaa.medishop.api.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Valinaa
 * @Date 2023/9/28 14:15
 * @Description 药品状态枚举
 */
@Schema(enumAsRef = true, name = "MedicineStatusEnum", title = "药品状态枚举",
        description = "0: out of stock 缺货;   1: under review 审核中;   "
                + "2: available 可购买;   3: sell out 售罄   ",
        allowableValues = {"0", "1", "2", "3"})
@Getter
@AllArgsConstructor
public enum MedicineStatusEnum {
    /**
     * 0: out of stock 没有库存<br/>
     * 1: under review 药品审核中<br/>
     * 2: available 可购买<br/>
     * 3: sell out 售罄
     */
    OUT_OF_STOCK("0", "缺货"),
    UNDER_REVIEW("1", "审核中"),
    AVAILABLE("2", "可购买"),
    SELL_OUT("3", "售罄");
    
    @EnumValue
    private final String code;
    private final String desc;
}
