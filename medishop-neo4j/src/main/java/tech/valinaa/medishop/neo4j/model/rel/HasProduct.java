package tech.valinaa.medishop.neo4j.model.rel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import tech.valinaa.medishop.neo4j.model.ProductEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 上午10:55
 * @Description Drug->HasProduct->Product
 */
@RelationshipProperties
@RequiredArgsConstructor
public class HasProduct {
    @TargetNode
    private final ProductEntity product;
    @RelationshipId
    private String id;
}
