package tech.valinaa.medishop.api.medicine.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * @author Valinaa
 * @Date 2023/9/28 14:03
 * @Description 药品详情返回结果模板
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MedicineDetailResponse extends MedicineResponse {
    private Long medicineId;
    private String medicineSpecification;
    private String description;
    private String sideEffects;
    private String ingredients;
    private String imageUrl;
    private LocalDateTime manufactureDate;
    private LocalDateTime validityDate;
}
