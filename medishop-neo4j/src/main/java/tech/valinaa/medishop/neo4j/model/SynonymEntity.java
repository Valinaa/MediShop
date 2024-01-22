package tech.valinaa.medishop.neo4j.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author Valinaa
 * @Date 2024/1/21 12:25
 * @Description 同义词
 */
@Data
@Node("synonym")
@RequiredArgsConstructor
public class SynonymEntity {
    @Id
    private final String name;
    @Property("language")
    private final String language;
    @Property("coder")
    private final String coder;
}
