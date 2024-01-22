package tech.valinaa.medishop.neo4j.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author Valinaa
 * @Date 2024/1/20 12:51
 * @Description ATC编码
 */

@Data
@Node("ATC-Code")
@RequiredArgsConstructor
public class ATCCodeEntity {
    @Id
    private final String code;
    @Property("first_level")
    private final String firstLevel;
    @Property("second_level")
    private final String secondLevel;
    @Property("third_level")
    private final String thirdLevel;
    @Property("fourth_level")
    private final String fourthLevel;
    @Property("first_level_description")
    private final String firstLevelDescription;
    @Property("second_level_description")
    private final String secondLevelDescription;
    @Property("third_level_description")
    private final String thirdLevelDescription;
    @Property("fourth_level_description")
    private final String fourthLevelDescription;
}
