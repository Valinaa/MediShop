package tech.valinaa.medishop.neo4j.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.neo4j.model.ATCCodeEntity;
import tech.valinaa.medishop.neo4j.service.ATCCodeService;

import java.util.List;

/**
 * ATCCode 查询Api
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:29
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Neo4j ATCCode 查询Api", description = "Neo4j ATCCode 查询接口")
public class ATCCodeQueryController implements Neo4jVer1API<ATCCodeEntity, String> {
    private final ATCCodeService atcCodeService;
    
    @Override
    public Result<ATCCodeEntity> findById(String id) {
        return null;
    }
    
    @Override
    @GetMapping("/atcCodes/drug/{drugId}")
    @Operation(summary = "ATC-Category->(ATC-Code)", description = "根据DrugId查询与该Drug的ATCCode")
    public Result<List<ATCCodeEntity>> findListByDrugId(@PathVariable String drugId) {
        return Result.convert(atcCodeService.findListByDrugId(drugId));
    }
    
    
}
