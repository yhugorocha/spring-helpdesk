package br.com.hugodev.helpdesk.mapper;

import br.com.hugodev.helpdesk.domain.User;
import br.com.hugodev.helpdesk.dto.CreateUserDto;
import br.com.hugodev.helpdesk.dto.UserDto;
import br.com.hugodev.helpdesk.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(CreateUserDto userDto);
    User toDomain(UserEntity userEntity);
    UserEntity toEntity(User userDomain);
    UserDto toDto(UserEntity userEntity);

}
