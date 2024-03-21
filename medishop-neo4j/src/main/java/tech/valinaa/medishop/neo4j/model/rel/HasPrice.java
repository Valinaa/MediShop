package tech.valinaa.medishop.neo4j.model.rel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import tech.valinaa.medishop.neo4j.model.PriceEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 下午2:46
 * @Description Drug->HasPrice->Price
 */
@RelationshipProperties
@RequiredArgsConstructor
public class HasPrice {
    @TargetNode
    private final PriceEntity price;
    @RelationshipId
    private String id;
}
