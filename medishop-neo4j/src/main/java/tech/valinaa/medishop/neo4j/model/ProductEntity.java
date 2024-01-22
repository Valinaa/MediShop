package tech.valinaa.medishop.neo4j.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author Valinaa
 * @Date 2024/1/21 12:19
 * @Description 产品
 */
@Data
@Node("Product")
@RequiredArgsConstructor
public class ProductEntity {
    @Id
    private final String id;
    @Property("approved")
    private final String approved;
    @Property("country")
    private final String country;
    @Property("dosage-form")
    private final String dosageForm;
    @Property("fda-application-number")
    private final String fdaApplicationNumber;
    @Property("generic")
    private final String generic;
    @Property("labeller")
    private final String labeller;
    @Property("name")
    private final String name;
    @Property("ndc-product-code")
    private final String ndcProductCode;
    @Property("over-the-counter")
    private final String overTheCounter;
    @Property("route")
    private final String route;
    @Property("source")
    private final String source;
    @Property("started-marketing-on")
    private final String startedMarketingOn;
    @Property("strength")
    private final String strength;
}
