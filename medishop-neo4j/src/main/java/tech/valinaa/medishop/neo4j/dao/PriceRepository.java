package tech.valinaa.medishop.neo4j.dao;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.valinaa.medishop.neo4j.model.PriceEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 下午3:04
 * @Description Price DTO
 */
@RepositoryRestResource(collectionResourceRel = "price", path = "price")
public interface PriceRepository extends Neo4jQueryRepository<PriceEntity, String> {
}
