package tech.valinaa.medishop.api.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.valinaa.medishop.api.product.response.ProductResponse;

@Tag(name = "药品管理Api", description = "药品相关Api")
@RequestMapping("/product")
public interface ProductApi {
    
    @Operation(summary = "获取单个药品信息")
    @GetMapping
    ProductResponse getOne();
    
    @Operation(summary = "新增单个药品信息")
    @PostMapping
    boolean addOne();
}
