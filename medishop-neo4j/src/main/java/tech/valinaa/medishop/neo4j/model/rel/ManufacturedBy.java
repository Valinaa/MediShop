package tech.valinaa.medishop.neo4j.model.rel;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import tech.valinaa.medishop.neo4j.model.CompanyEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 上午10:35
 * @Description Drug->ManufacturedBy->Company
 */

@RelationshipProperties
@RequiredArgsConstructor
public class ManufacturedBy {
    @TargetNode
    private final CompanyEntity company;
    // "1" or "yes" or "true" and the opposite
    @Getter
    private final String generic;
    @RelationshipId
    private String id;
}
