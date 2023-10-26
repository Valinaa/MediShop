package tech.valinaa.medishop.core.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import tech.valinaa.medishop.api.user.request.UserRequest;
import tech.valinaa.medishop.api.user.response.UserResponse;
import tech.valinaa.medishop.core.converter.annotation.AuthoritiesTransferMapper;
import tech.valinaa.medishop.core.converter.annotation.MappingIgnore;
import tech.valinaa.medishop.core.model.UserDO;

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
}
