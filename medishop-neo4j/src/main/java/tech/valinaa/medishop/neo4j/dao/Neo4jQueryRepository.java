package tech.valinaa.medishop.neo4j.dao;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Neo4j 查询通用接口
 * <p> 该接口继承自{@link Neo4jRepository}，并禁用所有的增删改操作，只允许查询
 *
 * @param <T> type of the domain class to map
 * @param <I> identifier type in the domain class
 * @author Valinaa
 * @Date 2024/1/21 13:17
 */
@NoRepositoryBean
public interface Neo4jQueryRepository<T, I> extends Neo4jRepository<T, I> {
    /**
     * 根据药品ID查询关联资源
     *
     * @param drugId 药品ID
     * @return 带有关联资源的药品列表
     */
    List<T> findListByDrugId(I drugId);
    
    @Override
    @RestResource(exported = false)
    <S extends T> S save(S entity);
    
    @Override
    @RestResource(exported = false)
    <S extends T> List<S> saveAll(Iterable<S> entities);
    
    @Override
    @RestResource(exported = false)
    void deleteById(I id);
    
    @Override
    @RestResource(exported = false)
    void delete(T entity);
    
    @Override
    @RestResource(exported = false)
    void deleteAllById(Iterable<? extends I> ids);
    
    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends T> entities);
    
    @Override
    @RestResource(exported = false)
    void deleteAll();
}
