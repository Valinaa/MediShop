package tech.valinaa.medishop.auth.security.user;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import tech.valinaa.medishop.auth.security.user.pojo.UserDO;
import tech.valinaa.medishop.auth.security.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.security.user.pojo.UserResponse;
import tech.valinaa.medishop.web.converter.BaseConverter;

/**
 * @author Valinaa
 * @Date 2023/10/2 20:32
 * @Description User转换器
 */
@SuppressWarnings("checkstyle:InterfaceIsType")
@Mapper
public interface UserConverter extends BaseConverter<UserRequest, UserResponse, UserDO> {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);
}
