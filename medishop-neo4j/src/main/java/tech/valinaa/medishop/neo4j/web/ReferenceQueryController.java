package tech.valinaa.medishop.neo4j.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.api.enums.ResultCodeEnum;
import tech.valinaa.medishop.neo4j.dao.ReferenceRepository;
import tech.valinaa.medishop.neo4j.model.ReferenceEntity;

import java.util.List;

/**
 * Reference Query Controller
 *
 * @author Valinaa
 * @Date 2024/1/22 9:59
 */

@RestController
@RequiredArgsConstructor
@Tag(name = "Neo4j Reference 查询Api", description = "Neo4j 参考文献 查询接口")
public class ReferenceQueryController implements Neo4jVer1API<ReferenceEntity, String> {
    private final ReferenceRepository referenceRepository;
    
    @Override
    @GetMapping("/reference/{refId}")
    public Result<ReferenceEntity> findById(@PathVariable String refId) {
        return referenceRepository.findById(refId).map(Result::convert).orElse(Result.failure(ResultCodeEnum.NO_SUCH_RECORD));
    }
    
    @Override
    @GetMapping("/references/citeByDrug/{drugId}")
    @Operation(summary = "Cite->(Reference)", description = "根据药品id查询引用的参考文献")
    public Result<List<ReferenceEntity>> findListByDrugId(@PathVariable String drugId) {
        return Result.convert(referenceRepository.findListByDrugId(drugId));
    }
}
