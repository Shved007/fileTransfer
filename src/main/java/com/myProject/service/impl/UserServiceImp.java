package com.myProject.service.impl;

import com.myProject.dto.UserDto;
import com.myProject.entity.User;
import com.myProject.exception.ResourceNotFoundException;
import com.myProject.repository.UserRepository;
import com.myProject.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImp(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto create(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        User persistedUser = userRepository.save(user);
        return modelMapper.map(persistedUser, UserDto.class);
    }

    @Override
    public UserDto findById(Long id) {
        User user = getUserById(id);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    private User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Resource not found by id [ \" + id + \" ]")
        );
    }
}
