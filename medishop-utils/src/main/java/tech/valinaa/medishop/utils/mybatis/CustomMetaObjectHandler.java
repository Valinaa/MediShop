package tech.valinaa.medishop.utils.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author Valinaa
 * @Date 2023/9/27 11:13
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject,"createTime", LocalDateTime::now,LocalDateTime.class);
        this.strictInsertFill(metaObject,"updateTime",LocalDateTime::now,LocalDateTime.class);
        this.strictInsertFill(metaObject, "deleted", ()-> 0,Integer.class);
//        this.strictInsertFill(metaObject, "version", ()-> 1,Integer.class);
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject,"updateTime",LocalDateTime::now,LocalDateTime.class);
    }
}
