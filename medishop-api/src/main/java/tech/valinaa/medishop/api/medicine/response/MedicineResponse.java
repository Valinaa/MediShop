package tech.valinaa.medishop.api.medicine.response;

import lombok.Data;
import tech.valinaa.medishop.core.model.enums.MedicineStatusEnum;

/**
 * @author Valinaa
 * @Date 2023/9/26 11:36
 * @Description 药品返回结果模板
 */
@Data
public class MedicineResponse {
    private String name;
    private String summary;
    private String price;
    private String stock;
    private String category;
    private MedicineStatusEnum status;
}
