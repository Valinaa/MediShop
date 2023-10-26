package tech.valinaa.medishop.core.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Valinaa
 * @Date 2023/9/28 14:36
 * @Description 药品详情DO
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("medicine_detail")
public class MedicineDetailDO extends BaseDO {
    private Long medicineId;
    private String medicineSpecification;
    private String description;
    private String sideEffects;
    private String ingredients;
    private String imageUrl;
    private LocalDateTime manufactureDate;
    private LocalDateTime validityDate;
}
