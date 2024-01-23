package tech.valinaa.medishop.neo4j.model.rel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import tech.valinaa.medishop.neo4j.model.DrugEntity;

/**
 * @author Valinaa
 * @Date 2024/1/22 10:33
 * @Description Drug->Has Interaction->Drug
 */
@RelationshipProperties
@RequiredArgsConstructor
public class DrugInteraction {
    @TargetNode
    private final DrugEntity drug;
    @Getter
    private final String description;
    @RelationshipId
    private String id;
}
