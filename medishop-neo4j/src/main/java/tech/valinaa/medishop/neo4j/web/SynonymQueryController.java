package tech.valinaa.medishop.neo4j.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.neo4j.model.SynonymEntity;
import tech.valinaa.medishop.neo4j.service.SynonymService;

import java.util.List;

/**
 * Synonym 查询Api
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:44
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Neo4j Synonym 查询Api", description = "Neo4j 同义词 查询接口")
public class SynonymQueryController implements Neo4jVer1API<SynonymEntity, String> {
    private final SynonymService synonymService;
    
    
    @Override
    public Result<SynonymEntity> findById(String id) {
        return null;
    }
    
    @Override
    @GetMapping("/synonyms/drug/{drugId}")
    @Operation(summary = "Has Synonym->(Synonym)", description = "根据药品id查询同义词")
    public Result<List<SynonymEntity>> findListByDrugId(@PathVariable String drugId) {
        return Result.convert(synonymService.findListByDrugId(drugId));
    }
}
