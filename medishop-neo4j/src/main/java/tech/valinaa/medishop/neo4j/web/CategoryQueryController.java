package tech.valinaa.medishop.neo4j.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.neo4j.model.CategoryEntity;
import tech.valinaa.medishop.neo4j.service.CategoryService;

import java.util.List;

/**
 * Category 查询Api
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:36
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Neo4j Category 查询Api", description = "Neo4j Category 查询接口")
public class CategoryQueryController implements Neo4jVer1API<CategoryEntity, String> {
    private final CategoryService categoryService;
    
    @Override
    public Result<CategoryEntity> findById(String id) {
        return null;
    }
    
    @Override
    @GetMapping("/categories/drug/{drugId}")
    @Operation(summary = "Has Category->(Category)", description = "根据drug id查询其关联的分类")
    public Result<List<CategoryEntity>> findListByDrugId(@PathVariable String drugId) {
        return Result.convert(categoryService.findListByDrugId(drugId));
    }
}
