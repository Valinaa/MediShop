package tech.valinaa.medishop.neo4j.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author Valinaa
 * @Date 2024/1/17 20:06
 * @Description 引用文献
 */

@Data
@Node("Reference")
@RequiredArgsConstructor
public class ReferenceEntity {
    @Id
    private final String refId;
    @Property("type")
    private final String type;
    @Property("core_sign")
    private final String coreSign;
}
