package tech.valinaa.medishop.neo4j.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

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
    private final String id;
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
    private final String created;
    @Property("updated")
    private final String updated;
}
