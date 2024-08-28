package br.com.hugodev.helpdesk.service;

import br.com.hugodev.helpdesk.dto.CreateUserDto;
import br.com.hugodev.helpdesk.dto.UserDto;
import br.com.hugodev.helpdesk.entity.UserEntity;
import br.com.hugodev.helpdesk.exception.BusinessException;
import br.com.hugodev.helpdesk.mapper.UserMapper;
import br.com.hugodev.helpdesk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    public UserDto createUser(CreateUserDto userDto){
        var userDomain = userMapper.toDomain(userDto);
        Optional<UserEntity> userEntity = userRepository.findByUsername(userDomain.getUsername());

        if(userEntity.isPresent()){
            throw new BusinessException("This username is already in use in the system");
        }

        userDomain.setPassword(passwordEncoder.encode(userDomain.getPassword()));
        userDomain.setCreatedAt(new Date());
        userDomain.setIsActive(true);

        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDomain)));
    }

}
