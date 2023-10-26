package tech.valinaa.medishop.core.mybatis;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
        var currentUsername = getCurrentUsername();
        this.strictInsertFill(metaObject, "creator", () -> currentUsername, String.class);
        this.strictInsertFill(metaObject, "modifier", () -> currentUsername, String.class);
        this.strictInsertFill(metaObject, "gmtCreated", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "gmtModified", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject, "deleted", () -> 0, Integer.class);
        // TODO 修改用户添加
//        this.strictInsertFill(metaObject, "version", ()-> 1,Integer.class);
    }
    
    @Override
    public void updateFill(MetaObject metaObject) {
        var currentUsername = getCurrentUsername();
        this.strictUpdateFill(metaObject, "gmtModified", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "modifier", () -> currentUsername, String.class);
    }
    
    private String getCurrentUsername() {
        return ((UserDetails)
                (SecurityContextHolder.getContext().getAuthentication().getPrincipal())
        ).getUsername();
    }
}
