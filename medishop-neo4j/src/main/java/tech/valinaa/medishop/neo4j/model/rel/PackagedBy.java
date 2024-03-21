package tech.valinaa.medishop.neo4j.model.rel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import tech.valinaa.medishop.neo4j.model.CompanyEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 上午10:34
 * @Description Drug->PackagedBy->Company
 */
@RelationshipProperties
@RequiredArgsConstructor
public class PackagedBy {
    @TargetNode
    private final CompanyEntity company;
    @RelationshipId
    private String id;
}
