package tech.valinaa.medishop.neo4j.model.rel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import tech.valinaa.medishop.neo4j.model.CategoryEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 下午2:47
 * @Description Drug->HasCategory->Category
 */
@RelationshipProperties
@RequiredArgsConstructor
public class HasCategory {
    @TargetNode
    private final CategoryEntity category;
    @RelationshipId
    private String id;
}
