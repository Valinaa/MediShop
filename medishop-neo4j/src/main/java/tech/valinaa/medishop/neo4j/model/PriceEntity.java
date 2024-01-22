package tech.valinaa.medishop.neo4j.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author Valinaa
 * @Date 2024/1/21 12:17
 * @Description 销售规格及对应价格
 */
@Data
@Node("Price")
@RequiredArgsConstructor
public class PriceEntity {
    @Id
    private final String id;
    @Property("cost")
    private final String cost;
    @Property("unit")
    private final String unit;
    @Property("currency")
    private final String currency;
    @Property("description")
    private final String description;
}
