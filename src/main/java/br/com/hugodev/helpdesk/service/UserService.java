package br.com.hugodev.helpdesk.service;

import br.com.hugodev.helpdesk.domain.User;
import br.com.hugodev.helpdesk.dto.UserDto;
import br.com.hugodev.helpdesk.entity.UserEntity;
import br.com.hugodev.helpdesk.exception.BusinessException;
import br.com.hugodev.helpdesk.mapper.UserMapper;
import br.com.hugodev.helpdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public User createUser(UserDto dto){
        Optional<UserEntity> userEntity = userRepository.findByUsername(dto.username());

        if(userEntity.isPresent()){
            throw new BusinessException("This username is already in use in the system");
        }

        UserEntity newUserEntity = userMapper.toEntity(dto);
        newUserEntity.setCreatedAt(new Date());
        newUserEntity.setIsActive(true);
        return userMapper.toDomain(userRepository.save(newUserEntity));
    }

}
