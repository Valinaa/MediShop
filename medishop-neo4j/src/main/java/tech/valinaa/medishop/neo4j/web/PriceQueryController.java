package tech.valinaa.medishop.neo4j.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.neo4j.model.PriceEntity;
import tech.valinaa.medishop.neo4j.service.PriceService;

import java.util.List;

/**
 * Price 查询Api
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:39
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Neo4j Price 查询Api", description = "Neo4j 价格 查询接口")
public class PriceQueryController implements Neo4jVer1API<PriceEntity, String> {
    private final PriceService priceService;
    
    
    @Override
    public Result<PriceEntity> findById(String id) {
        return null;
    }
    
    @Override
    @GetMapping("/prices/drug/{drugId}")
    @Operation(summary = "Has Price->(Price)", description = "根据药品id查询相关价格")
    public Result<List<PriceEntity>> findListByDrugId(@PathVariable String drugId) {
        return Result.convert(priceService.findListByDrugId(drugId));
    }
}
