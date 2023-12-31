package tech.valinaa.medishop.api;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Valinaa
 * @Date 2023/9/26 11:45
 * @Description 表数据对象基类
 */
@Data
public class BaseDO implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    @TableId(type = IdType.AUTO)
    private Long id;
    
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreated;
    
    @TableField(fill = FieldFill.INSERT_UPDATE, update = "now()")
    private LocalDateTime gmtModified;
    
    @TableField(fill = FieldFill.INSERT)
    private String creator;
    
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String modifier;
    
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
}
