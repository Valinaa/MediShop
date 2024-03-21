package tech.valinaa.medishop.neo4j.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.valinaa.medishop.neo4j.model.BrandEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 下午3:01
 * @Description Brand DTO
 */
@RepositoryRestResource(path = "brand", collectionResourceRel = "brand")
public interface BrandRepository extends Neo4jQueryRepository<BrandEntity, String> {
}
