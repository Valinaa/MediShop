package tech.valinaa.medishop.neo4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tech.valinaa.medishop.neo4j.dao.DrugRepository;
import tech.valinaa.medishop.neo4j.model.DrugEntity;

import java.util.List;
import java.util.Optional;

/**
 * Neo4j Query For Drug
 *
 * @author Valinaa
 * @Date 2024/1/28 21:08
 */
@Service
@RequiredArgsConstructor
public final class DrugService {
    private final DrugRepository drugRepository;
    
    /**
     * 根据id查询实体
     *
     * @param drugId 实体id
     * @return 实体
     */
    public Optional<DrugEntity> findById(@PathVariable String drugId) {
        return drugRepository.findById(drugId);
    }
    
    /**
     * 根据药品ID查询关联资源
     *
     * @param drugId 药品ID
     * @return 带有关联资源的药品列表
     */
    public List<DrugEntity> findListByDrugId(String drugId) {
        return drugRepository.findListByDrugId(drugId);
    }
}
