package tech.valinaa.medishop.neo4j.dao;

import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import tech.valinaa.medishop.neo4j.model.ReferenceEntity;

import java.util.List;

/**
 * @author Valinaa
 * @Date 2024/1/22 9:46
 * @Description Reference DTO
 */

@RepositoryRestResource(path = "reference", collectionResourceRel = "reference")
public interface ReferenceRepository extends Neo4jQueryRepository<ReferenceEntity, String> {
    
    @Override
    @RestResource(rel = "drug", path = "drug")
    @Query("MATCH r=(drug:Drug)-[:`Cite`]->() WHERE drug.id = $drugId RETURN r LIMIT 25")
    List<ReferenceEntity> findListByDrugId(@Param("drugId") String drugId);
}
