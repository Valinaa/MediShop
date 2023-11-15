package tech.valinaa.medishop.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.valinaa.medishop.api.BaseDO;
import tech.valinaa.medishop.api.enums.MedicineStatusEnum;

import java.math.BigDecimal;

/**
 * @author Valinaa
 * @Date 2023/9/26 11:57
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("medicine")
public class MedicineDO extends BaseDO {
    private String name;
    private String summary;
    private BigDecimal price;
    private Integer stock;
    private String category;
    private MedicineStatusEnum status;
}
