package tech.valinaa.medishop.neo4j.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.enums.ResultCodeEnum;
import tech.valinaa.medishop.neo4j.model.DrugEntity;
import tech.valinaa.medishop.neo4j.service.DrugService;

import java.util.List;

/**
 * Drug 查询Api
 *
 * @author Valinaa
 * @Date 2024/1/22 12:45
 */

@RestController
@RequiredArgsConstructor
@Tag(name = "Neo4j Drug 查询Api", description = "Neo4j 药品 查询接口")
public class DrugQueryController implements Neo4jVer1API<DrugEntity, String> {
    
    private final DrugService drugService;
    
    @Override
    @GetMapping("/drug/{drugId}")
    public Result<DrugEntity> findById(@PathVariable String drugId) {
        return drugService.findById(drugId).map(Result::convert).orElse(Result.failure(ResultCodeEnum.NO_SUCH_RECORD));
    }
    
    @Override
    @GetMapping("/drugs/interactions")
    @Operation(summary = "Has Interaction->(Drug)", description = "根据DrugId查询与其有相互作用的其它Drug")
    public Result<List<DrugEntity>> findListByDrugId(String drugId) {
        return Result.convert(drugService.findListByDrugId(drugId));
    }
}
