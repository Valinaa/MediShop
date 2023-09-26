package tech.valinaa.medishop.core.model.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Valinaa
 * @Date 2023/9/26 11:57
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("product")
public class ProductDO extends BaseDO{
    private String name;
    private String description;
    private String price;
    private String stock;
    private String category;
    private String image;
    private String status;
}
