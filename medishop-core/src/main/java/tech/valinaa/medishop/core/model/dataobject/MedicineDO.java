package tech.valinaa.medishop.core.model.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import tech.valinaa.medishop.core.model.enums.MedicineStatusEnum;

/**
 * @author Valinaa
 * @Date 2023/9/26 11:57
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("medicine")
public class MedicineDO extends BaseDO{
    private String name;
    private String summary;
    private String price;
    private String stock;
    private String category;
    private MedicineStatusEnum status;
}
