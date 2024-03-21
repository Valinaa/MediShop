package tech.valinaa.medishop.neo4j.model.rel;

import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.RelationshipId;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;
import tech.valinaa.medishop.neo4j.model.SynonymEntity;

/**
 * @author Valinaa
 * @Date 2024/3/21 上午10:54
 * @Description Drug->HasSynonyms->Synonyms
 */
@RelationshipProperties
@RequiredArgsConstructor
public class HasSynonyms {
    @TargetNode
    private final SynonymEntity synonym;
    
    @RelationshipId
    private String id;
}
