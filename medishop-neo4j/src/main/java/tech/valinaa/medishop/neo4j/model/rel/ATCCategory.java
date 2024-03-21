package tech.valinaa.medishop.neo4j.model.rel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import tech.valinaa.medishop.neo4j.model.ATCCodeEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 上午10:24
 * @Description Drug->ATC-Category->ATC-Code
 */
@RelationshipProperties
@RequiredArgsConstructor
public class ATCCategory {
    
    @TargetNode
    private final ATCCodeEntity category;
    @RelationshipId
    private String id;
}
