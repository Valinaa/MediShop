package tech.valinaa.medishop.api.product.response;

import lombok.Data;

/**
 * @author Valinaa
 * @Date 2023/9/26 11:36
 * @Description 药品返回结果模板
 */
@Data
public class ProductResponse {
    private String name;
    private String description;
    private String price;
    private String stock;
    private String category;
    private String image;
    private String status;
}
