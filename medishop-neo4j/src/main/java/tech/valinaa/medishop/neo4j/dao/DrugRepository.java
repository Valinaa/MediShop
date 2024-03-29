package tech.valinaa.medishop.neo4j.dao;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import tech.valinaa.medishop.neo4j.model.DrugEntity;

import java.util.List;

/**
 * @author Valinaa
 * @Date 2024/1/21 12:31
 * @Description Drug DTO
 */
@RepositoryRestResource(path = "drug", collectionResourceRel = "drug")
public interface DrugRepository extends Neo4jQueryRepository<DrugEntity, String> {
    @Override
    @RestResource(rel = "interactions", path = "interactions")
    @Query("MATCH r=(drug:Drug)-[:`Has Interaction`]->() WHERE drug.id = $drugId RETURN r LIMIT 25")
    List<DrugEntity> findListByDrugId(@Param("drugId") String drugId);
}
