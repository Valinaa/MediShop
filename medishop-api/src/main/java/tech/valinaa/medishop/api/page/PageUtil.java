package tech.valinaa.medishop.api.page;

import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.core.util.CollectionUtils;
import lombok.experimental.UtilityClass;
import tech.valinaa.medishop.api.BaseDO;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Valinaa
 * @Date 2023/11/15 14:29
 * @Description 分页工具类
 */

@UtilityClass
public class PageUtil {
    
    public static <T> Page<T> getPage(BasePageRequest request, Class<?> c) {
        return getPage(request.getPageNo(), request.getPageSize(), request.getDesc(), request.getOrderBy(), c);
    }
    
    public static <T> Page<T> getPage(Integer pageNo, Integer pageSize, Boolean desc, String orderBy, Class<?> c) {
        if (pageSize == -1) {
            pageSize = 10000;
        }
        var page = new Page<T>(pageNo, pageSize);
        if (!orderBy.isBlank()) {
            var fields = getFields(c, orderBy);
            if (CollectionUtils.isNotEmpty(fields)) {
                var sqlFiled = CharSequenceUtil.toUnderlineCase(orderBy);
                return page.addOrder(
                        Boolean.TRUE.equals(desc)
                                ? OrderItem.desc(sqlFiled)
                                : OrderItem.asc(sqlFiled));
            }
        }
        return page;
    }
    
    
    /**
     * 防sql注入 过滤字段
     *
     * @param c       class
     * @param orderBy 排序字段
     * @return 字段列表
     */
    private static List<Field> getFields(Class<?> c, String orderBy) {
        var fields = Arrays.stream(BaseDO.class.getDeclaredFields())
                .filter(f -> Objects.equals(f.getName(), orderBy))
                .toList();
        if (fields.isEmpty()) {
            fields = Arrays.stream(c.getDeclaredFields())
                    .filter(f -> Objects.equals(f.getName(), orderBy))
                    .toList();
        }
        return fields;
    }
}
