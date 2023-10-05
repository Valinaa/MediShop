package tech.valinaa.medishop.auth.security.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tech.valinaa.medishop.auth.security.user.pojo.UserDO;
import tech.valinaa.medishop.auth.security.user.pojo.UserRequest;
import tech.valinaa.medishop.auth.security.user.pojo.UserResponse;
import tech.valinaa.medishop.web.converter.BaseConverter;
import tech.valinaa.medishop.web.converter.mapping.MappingIgnore;

import java.util.List;

/**
 * @author Valinaa
 * @Date 2023/10/2 20:32
 * @Description User转换器
 */
@SuppressWarnings("checkstyle:InterfaceIsType")
@Mapper
public interface UserConverter extends BaseConverter<UserRequest, UserResponse, UserDO> {
    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);
    
    @Override
    @MappingIgnore
    @Mapping(source = "authorities", target = "authorities", qualifiedBy = AuthoritiesTransferMapper.class)
    UserDO req2DO(UserRequest userRequest);
    
    @Override
    @Mapping(source = "authorities", target = "authorities", qualifiedBy = AuthoritiesTransferMapper.class)
    UserResponse dao2Response(UserDO dao);
    
    @Override
    @Mapping(source = "authorities", target = "authorities", qualifiedBy = AuthoritiesTransferMapper.class)
    UserResponse req2Response(UserRequest userRequest);
    
    /**
     * authorities转换
     *
     * @param value List<{@link SimpleGrantedAuthority}>
     * @return String[]
     */
    @AuthoritiesTransferMapper
    static String[] authoritiesTransfer(List<SimpleGrantedAuthority> value) {
        var strings = new String[value.size()];
        value.forEach(i -> strings[value.indexOf(i)] = i.getAuthority());
        return strings;
    }
}
