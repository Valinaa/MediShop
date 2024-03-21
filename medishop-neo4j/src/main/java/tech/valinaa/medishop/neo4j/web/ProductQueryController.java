package tech.valinaa.medishop.neo4j.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.neo4j.model.ProductEntity;
import tech.valinaa.medishop.neo4j.service.ProductService;

import java.util.List;

/**
 * Product 查询Api
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:41
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Neo4j Product 查询Api", description = "Neo4j 产品 查询接口")
public class ProductQueryController implements Neo4jVer1API<ProductEntity, String> {
    private final ProductService productService;
    
    
    @Override
    public Result<ProductEntity> findById(String id) {
        return null;
    }
    
    @Override
    @GetMapping("/products/drug/{drugId}")
    @Operation(summary = "Has Product->(Product)", description = "根据药品id查询产品")
    public Result<List<ProductEntity>> findListByDrugId(@PathVariable String drugId) {
        return Result.convert(productService.findListByDrugId(drugId));
    }
}
