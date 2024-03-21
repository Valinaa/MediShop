package tech.valinaa.medishop.neo4j.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.valinaa.medishop.neo4j.model.ProductEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 下午3:04
 * @Description Product DTO
 */
@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository extends Neo4jQueryRepository<ProductEntity, String> {
}
