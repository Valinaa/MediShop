package tech.valinaa.medishop.api.product.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author Valinaa
 * @Date 2023/9/26 17:40
 * @Description 药品请求模板
 */
@Data
@Schema(title = "药品请求实体")
public class ProductRequest {
    
    @NotBlank
    @Schema(title = "medicine name", description = "药品名称",type = "string",requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;
    
    @Schema(title = "description", description = "药品描述",type = "string",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String description;
    
    @Schema(title = "price", description = "药品价格",type = "string",requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String price;
    
    private String stock;
    
    @NotBlank
    private String category;
    
    private String image;
    
    private String status;
}
