package tech.valinaa.medishop.neo4j.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author Valinaa
 * @Date 2024/1/20 13:02
 * @Description 公司
 */

@Data
@Node("Company")
@RequiredArgsConstructor
public class CompanyEntity {
    @Id
    private final String name;
    @Property("url")
    private final String url;
}
