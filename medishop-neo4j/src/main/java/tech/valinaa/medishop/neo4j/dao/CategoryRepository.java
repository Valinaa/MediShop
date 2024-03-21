package tech.valinaa.medishop.neo4j.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.valinaa.medishop.neo4j.model.CategoryEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 下午3:02
 * @Description Category DTO
 */
@RepositoryRestResource(collectionResourceRel = "category", path = "category")
public interface CategoryRepository extends Neo4jQueryRepository<CategoryEntity, String> {
}
