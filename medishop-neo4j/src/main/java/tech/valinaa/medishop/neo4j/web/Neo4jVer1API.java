package tech.valinaa.medishop.neo4j.web;

import org.springframework.web.bind.annotation.RequestMapping;
import tech.valinaa.medishop.api.Result;

import java.util.List;

/**
 * Neo4j v1版本的接口, 仅支持查询
 *
 * @param <T> 实体类型
 * @param <I> 实体id类型
 * @author Valinaa
 * @Date 2024/1/22 10:38
 */
@RequestMapping("/api/v1/neo4j")
public interface Neo4jVer1API<T, I> {
    /**
     * 根据药品id查询实体列表
     *
     * @param drugId 药品id
     * @return 实体列表
     */
    Result<List<T>> findListByDrugId(String drugId);
    
    /**
     * 根据id查询实体
     *
     * @param id 实体id
     * @return 实体
     */
    Result<T> findById(I id);
}
