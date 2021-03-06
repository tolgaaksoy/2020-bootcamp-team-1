package com.eteration.foodstuff.service;

import com.eteration.foodstuff.dto.RegistrationDto;
import com.eteration.foodstuff.dto.UserDto;
import com.eteration.foodstuff.mapper.UserMapper;
import com.eteration.foodstuff.model.User;
import com.eteration.foodstuff.repository.UserRepository;
import com.eteration.foodstuff.response.RegistrationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto addUser(UserDto userDto) {
        return userMapper.toUserDto(userRepository.save(userMapper.toUser(userDto)));
    }

    @Override
    public UserDto getUser(long userId) {
        return userMapper
                .toUserDto(userRepository
                        .findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("User : " + userId + "does not exist !")));
    }
    @Override
    public UserDto getByUserName(String username) {
        return userMapper.toUserDto(userRepository.findByUsername(username));
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User userDb = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("User : " + userDto.getId() + "does not exist !"));
        userDb.setEmail(userDto.getEmail());
        userDb.setAbout(userDto.getAbout());
        userDb.setUsername(userDto.getUsername());
        userDb.setPassword(userDb.getPassword());
        return userMapper.toUserDto(userRepository.save(userDb));
    }

    public RegistrationResponse register(RegistrationDto registrationDTO) {
        User user = new User();
        user.setEmail(registrationDTO.getEmail());
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDTO.getPassword()));
        userRepository.save(user);
        return new RegistrationResponse("succes", true);
    }

    @Override
    public List<UserDto> getUsers() {
        return userMapper.toUserDtoList(userRepository.findAll());
    }
}
