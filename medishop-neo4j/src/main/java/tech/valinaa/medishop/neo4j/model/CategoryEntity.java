package tech.valinaa.medishop.neo4j.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

/**
 * @author Valinaa
 * @Date 2024/1/21 12:13
 * @Description 分类（Mesh）
 */
@Data
@Node("Category")
@RequiredArgsConstructor
public class CategoryEntity {
    @Id
    private final String category;
    @Property("mesh_id")
    private final String meshId;
}
