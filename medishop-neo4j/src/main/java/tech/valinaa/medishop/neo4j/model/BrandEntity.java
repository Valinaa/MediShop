package tech.valinaa.medishop.neo4j.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author Valinaa
 * @Date 2024/1/20 12:57
 * @Description 国际品牌
 */

@Data
@Node("Brand")
@RequiredArgsConstructor
public class BrandEntity {
    @Id
    private final Long id;
    @Property("company")
    private final String company;
    @Property("name")
    private final String name;
}
