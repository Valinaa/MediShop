package tech.valinaa.medishop.neo4j.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.neo4j.model.BrandEntity;
import tech.valinaa.medishop.neo4j.service.BrandService;

import java.util.List;

/**
 * Brand 查询Api
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:32
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Neo4j Brand 查询Api", description = "Neo4j Brand 查询接口")
public class BrandQueryController implements Neo4jVer1API<BrandEntity, String> {
    private final BrandService brandService;
    
    @Override
    public Result<BrandEntity> findById(String id) {
        return null;
    }
    
    @Override
    @GetMapping("/brands/drug/{drugId}")
    @Operation(summary = "Has Brand->(Brand)", description = "根据drug id查询其关联的品牌")
    public Result<List<BrandEntity>> findListByDrugId(@PathVariable String drugId) {
        return Result.convert(brandService.findListByDrugId(drugId));
    }
    
    
}
