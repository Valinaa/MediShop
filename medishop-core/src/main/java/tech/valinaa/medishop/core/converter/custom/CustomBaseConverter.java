package tech.valinaa.medishop.core.converter.custom;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import tech.valinaa.medishop.api.page.PageResult;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Valinaa
 * @Date 2023/11/23 10:38
 * @Description 自定义基类转换器
 */
public abstract class CustomBaseConverter {
    protected CustomBaseConverter() {
    }
    
    /**
     * page 分页转换
     *
     * @param page     分页对象
     * @param function 转换函数
     * @param <T>      源类型
     * @param <R>      目标类型
     * @return 转换后的分页结果集
     */
    public static <T, R> PageResult<R> convertPageResult(Page<T> page, Function<T, R> function) {
        PageResult<R> pageResult = new PageResult<>();
        if (page == null) {
            return PageResult.empty();
        }
        pageResult.setPageNo(page.getCurrent());
        pageResult.setPageSize(page.getSize());
        pageResult.setTotalPages(page.getPages());
        pageResult.setTotal(page.getTotal());
        for (T t : page.getRecords()) {
            pageResult.getPageList().add(function.apply(t));
        }
        return pageResult;
    }
    
    /**
     * page 结果集转换
     *
     * @param page     page 结果集
     * @param function 转换函数
     * @param <T>      源类型
     * @param <R>      目标类型
     * @return 转换后的分页结果集
     */
    public static <T, R> PageResult<R> convertPageResult(PageResult<T> page, Function<T, R> function) {
        PageResult<R> pageResult = new PageResult<>();
        if (page == null) {
            return PageResult.empty();
        }
        pageResult.setPageNo(page.getPageNo());
        pageResult.setPageSize(page.getPageSize());
        pageResult.setTotalPages(page.getTotalPages());
        pageResult.setTotal(page.getTotal());
        for (T t : page.getPageList()) {
            pageResult.getPageList().add(function.apply(t));
        }
        return pageResult;
    }
    
    /**
     * list 转换
     *
     * @param source   源list
     * @param function 转换函数
     * @param <T>      源类型
     * @param <R>      目标类型
     * @return 转换后的list
     */
    public static <T, R> List<R> convertList(List<T> source, Function<T, R> function) {
        List<R> list = new ArrayList<>();
        if (CollUtil.isEmpty(source)) {
            return list;
        }
        for (T t : source) {
            list.add(function.apply(t));
        }
        return list;
    }
}
