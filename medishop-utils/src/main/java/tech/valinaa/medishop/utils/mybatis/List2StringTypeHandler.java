package tech.valinaa.medishop.utils.mybatis;

import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;

/**
 * @author Valinaa
 * @Date 2023/10/3 15:14
 * @Description 通用List<String>类型处理器
 */
public class List2StringTypeHandler extends ListTypeHandler<String> {
    @Override
    protected TypeReference<List<String>> specificType() {
        return new TypeReference<>() {
        };
    }
}
