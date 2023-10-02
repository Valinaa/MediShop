package tech.valinaa.medishop.api.medicine.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import tech.valinaa.medishop.core.model.enums.MedicineStatusEnum;

/**
 * @author Valinaa
 * @Date 2023/9/26 17:40
 * @Description 药品请求模板
 */
@Data
@Schema(title = "药品请求实体")
public class MedicineRequest {
    @NotBlank
    @Schema(title = "medicine name", description = "名称", type = "string", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    @Schema(title = "summary", description = "简介", type = "string", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String summary;
    @NotBlank
    @Schema(title = "price", description = "价格", type = "string", requiredMode = Schema.RequiredMode.REQUIRED)
    private String price;
    @NotBlank
    @Schema(title = "stock", description = "库存", type = "string", requiredMode = Schema.RequiredMode.REQUIRED)
    private String stock;
    @NotBlank
    @Schema(title = "category", description = "类别", type = "string", requiredMode = Schema.RequiredMode.REQUIRED)
    private String category;
    @NotBlank
    @Schema(title = "status", description = "状态", type = "string", requiredMode = Schema.RequiredMode.REQUIRED)
    private MedicineStatusEnum status;
}
