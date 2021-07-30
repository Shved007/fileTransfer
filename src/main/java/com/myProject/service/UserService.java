package com.myProject.service;

import com.myProject.dto.UserDto;
import java.util.List;

public interface UserService {

    UserDto create(UserDto userDto);

    UserDto findById(Long id);

    List<UserDto> findAll();

}
