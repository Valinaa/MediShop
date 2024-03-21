package tech.valinaa.medishop.neo4j.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.valinaa.medishop.neo4j.model.CompanyEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 下午3:03
 * @Description Company DTO
 */
@RepositoryRestResource(collectionResourceRel = "company", path = "company")
public interface CompanyRepository extends Neo4jQueryRepository<CompanyEntity, String> {
}
