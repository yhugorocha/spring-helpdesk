package br.com.hugodev.helpdesk.mapper;

import br.com.hugodev.helpdesk.domain.User;
import br.com.hugodev.helpdesk.dto.UserDto;
import br.com.hugodev.helpdesk.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity user);

    UserEntity toEntity(UserDto dto);

}
