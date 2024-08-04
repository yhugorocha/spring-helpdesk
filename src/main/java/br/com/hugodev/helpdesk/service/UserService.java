package br.com.hugodev.helpdesk.service;

import br.com.hugodev.helpdesk.domain.User;
import br.com.hugodev.helpdesk.dto.UserDto;
import br.com.hugodev.helpdesk.entity.UserEntity;
import br.com.hugodev.helpdesk.mapper.UserMapper;
import br.com.hugodev.helpdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public User createUser(UserDto dto){

        UserEntity userEntity = userMapper.toEntity(dto);
        userEntity.setCreatedAt(new Date());
        userEntity.setIsActive(true);

        return userMapper.toDomain(userRepository.save(userEntity));
    }

}
