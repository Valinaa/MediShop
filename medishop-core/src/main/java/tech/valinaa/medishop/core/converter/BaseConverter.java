package tech.valinaa.medishop.core.converter;

import tech.valinaa.medishop.core.converter.annotation.MappingIgnore;

/**
 * @param <R1> 请求实体
 * @param <R2> 响应实体
 * @param <D>  DO对象实体
 * @author Valinaa
 * @Date 2023/9/27 9:21
 * @Description 通用转换器
 */
public interface BaseConverter<R1, R2, D> {
    
    /**
     * 请求实体转DO对象
     *
     * @param reqEntity 请求实体
     * @return DO对象
     */
    @MappingIgnore
    D req2DO(R1 reqEntity);
    
    /**
     * 请求实体转响应实体
     *
     * @param reqEntity DO对象
     * @return 响应实体
     */
    R2 req2Response(R1 reqEntity);
    
    /**
     * DO对象转响应实体
     *
     * @param dao DO对象
     * @return 响应实体
     */
    R2 dao2Response(D dao);
}
