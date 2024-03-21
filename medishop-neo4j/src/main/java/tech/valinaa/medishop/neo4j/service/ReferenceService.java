package tech.valinaa.medishop.neo4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tech.valinaa.medishop.neo4j.dao.ReferenceRepository;
import tech.valinaa.medishop.neo4j.model.ReferenceEntity;

import java.util.List;
import java.util.Optional;

/**
 * Neo4j Query For Reference
 *
 * @author Valinaa
 * @Date 2024/1/28 21:20
 */
@Service
@RequiredArgsConstructor
public final class ReferenceService {
    private final ReferenceRepository referenceRepository;
    
    /**
     * 根据id查询实体
     *
     * @param refId 引用ID
     * @return 实体
     */
    public Optional<ReferenceEntity> findById(@PathVariable String refId) {
        return referenceRepository.findById(refId);
    }
    
    /**
     * 根据药品ID查询关联资源
     *
     * @param drugId 药品ID
     * @return 带有关联资源的药品列表
     */
    public List<ReferenceEntity> findListByDrugId(String drugId) {
        return referenceRepository.findListByDrugId(drugId);
    }
}
