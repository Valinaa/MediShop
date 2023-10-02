package tech.valinaa.medishop.api.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Valinaa
 * @Date 2023/9/26 11:27
 * @Description 分页请求模版
 */
@Data
@SuppressWarnings("checkstyle:MagicNumber")
public class BasePageRequest {
    @Schema(title = "当前页数", type = "Integer")
    private Integer pageNo = 1;
    
    @Schema(title = "每页显示多少条", type = "Integer")
    private Integer pageSize = 1000;

    @Schema(title = "是否倒叙,true 倒叙, false 正序", type = "Boolean")
    private Boolean desc = true;

    @Schema(title = "排序字段", type = "String")
    private String orderBy = "modifiedTime";

    private Integer start;

    public Integer getStart() {
        return (pageNo - 1) * pageSize;
    }
}
