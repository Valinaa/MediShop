package tech.valinaa.medishop.neo4j.model.rel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import tech.valinaa.medishop.neo4j.model.ReferenceEntity;

/**
 * @author Valinaa
 * @Date 2024/1/22 10:23
 * @Description Drug->Cite->Reference
 */

@RelationshipProperties
@RequiredArgsConstructor
public class Cite {
    @TargetNode
    private final ReferenceEntity reference;
    
    @Getter
    private final String citation;
    
    @RelationshipId
    private String id;
    
}
