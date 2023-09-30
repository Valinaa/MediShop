package tech.valinaa.medishop.api.medicine.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Valinaa
 * @Date 2023/9/28 15:23
 */
@Data
@Schema(title = "药品添加详情请求实体")
public class MedicineDetailRequest {
    @NotBlank
    @Schema(title = "medicine id", description = "药品ID",type = "long",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long medicineId;
    @Schema(title = "medicine specification", description = "药品规格",type = "string",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String medicineSpecification;
    @Schema(title = "medicine description", description = "药品描述",type = "string",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;
    @Schema(title = "medicine side effects", description = "药品副作用",type = "string",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String sideEffects;
    @Schema(title = "medicine ingredients", description = "药品成分",type = "string",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String ingredients;
    @Schema(title = "medicine image URL", description = "药品图片链接",type = "string",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String imageUrl;
    @Schema(title = "medicine manufacture date", description = "药品生产日期",type = "LocalDateTime",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime manufactureDate;
    @Schema(title = "medicine validity date", description = "药品有效期",type = "LocalDateTime",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private LocalDateTime validityDate;
}
