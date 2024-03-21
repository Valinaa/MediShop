package tech.valinaa.medishop.neo4j.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.valinaa.medishop.neo4j.model.ATCCodeEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 下午2:55
 * @Description ATCCode DTO
 */
@RepositoryRestResource(path = "atc-code", collectionResourceRel = "atc-code")
public interface ATCCodeRepository extends Neo4jQueryRepository<ATCCodeEntity, String> {
}
