package tech.valinaa.medishop.neo4j.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import tech.valinaa.medishop.neo4j.model.rel.Cite;
import tech.valinaa.medishop.neo4j.model.rel.DrugInteraction;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Valinaa
 * @Date 2024/1/17 16:26
 * @Description 药品
 */
@Data
@Node("Drug")
@RequiredArgsConstructor
public class DrugEntity {
    @Id
    @Property("id")
    private final String drugId;
    @Property("name")
    private final String name;
    @Property("state")
    private final String state;
    @Property("type")
    private final String type;
    @Property("affected-organisms")
    private final String organisms;
    @Property("description")
    private final String description;
    @Property("food-interactions")
    private final String foodInteractions;
    @Property("groups")
    private final String groups;
    @Property("indication")
    private final String indication;
    @Property("created")
    private final ZonedDateTime created;
    @Property("updated")
    private final ZonedDateTime updated;
    
    @Relationship(type = "Cite", direction = Relationship.Direction.OUTGOING)
    private List<Cite> cites;
    @Relationship(type = "Has Interaction", direction = Relationship.Direction.OUTGOING)
    private List<DrugInteraction> interactions;
}
