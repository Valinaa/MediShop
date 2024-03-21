package tech.valinaa.medishop.neo4j.web;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tech.valinaa.medishop.api.Result;
import tech.valinaa.medishop.neo4j.model.CompanyEntity;
import tech.valinaa.medishop.neo4j.service.CompanyService;

import java.util.List;

/**
 * Company 查询Api
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:37
 */
@RestController
@RequiredArgsConstructor
@Tag(name = "Neo4j Company 查询Api", description = "Neo4j 公司 查询接口")
public class CompanyQueryController implements Neo4jVer1API<CompanyEntity, String> {
    private final CompanyService companyService;
    
    
    @Override
    public Result<CompanyEntity> findById(String id) {
        return null;
    }
    
    // todo 分为Packaged By 和Manufactured By
    @Override
    @GetMapping("/companies/drug/{drugId}")
    @Operation(summary = "Produce->(Company)", description = "根据药品id查询生产的公司")
    public Result<List<CompanyEntity>> findListByDrugId(@PathVariable String drugId) {
        return Result.convert(companyService.findListByDrugId(drugId));
    }
}
