package tech.valinaa.medishop.neo4j.model.rel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import tech.valinaa.medishop.neo4j.model.BrandEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 下午2:47
 * @Description Drug->HasBrand->Brand
 */
@RelationshipProperties
@RequiredArgsConstructor
public class HasBrand {
    @TargetNode
    private final BrandEntity brand;
    @RelationshipId
    private String id;
}
