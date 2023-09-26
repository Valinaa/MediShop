package tech.valinaa.medishop.api.page;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Valinaa
 * @Date 2023/9/26 11:29
 * @Description 分页返回模板
 */
@Data
public class PageResult<T> {
    /**
     * 每页显示条数
     */
    private long pageSize;

    /**
     * 总条数
     */
    private long total;

    /**
     * 开始条数
     */
    private long start;

    /**
     * 当前页
     */
    private long pageNo;

    /**
     * 总页数
     */
    private long totalPages;

    /**
     * 数据
     */
    private List<T> pageList = new ArrayList<>();

    public PageResult(int totalCount) {
        this.total = totalCount;
    }

    public PageResult() {

    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>();
    }

}
