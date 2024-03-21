package tech.valinaa.medishop.neo4j.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.valinaa.medishop.neo4j.model.SynonymEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 下午3:05
 * @Description Synonym DTO
 */
@RepositoryRestResource(collectionResourceRel = "synonym", path = "synonym")
public interface SynonymRepository extends Neo4jQueryRepository<SynonymEntity, String> {
}
