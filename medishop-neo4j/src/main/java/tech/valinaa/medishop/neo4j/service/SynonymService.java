package tech.valinaa.medishop.neo4j.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.valinaa.medishop.neo4j.dao.SynonymRepository;
import tech.valinaa.medishop.neo4j.model.SynonymEntity;

import java.util.List;

/**
 * 同义词 Service
 *
 * @author Valinaa
 * @Date 2024/3/21 下午3:11
 */
@Service
@RequiredArgsConstructor
public final class SynonymService {
    private final SynonymRepository synonymRepository;
    
    /**
     * 根据药品ID查询关联资源
     *
     * @param drugId 药品ID
     * @return 带有关联资源的药品列表
     */
    public List<SynonymEntity> findListByDrugId(String drugId) {
        return synonymRepository.findListByDrugId(drugId);
    }
}
