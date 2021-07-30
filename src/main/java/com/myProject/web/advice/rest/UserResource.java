package com.myProject.web.advice.rest;

import com.myProject.dto.UserDto;
import com.myProject.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserResource {
    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody  UserDto userDto){
        userService.create(userDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<?> findAll(){
        List<UserDto> userDtoList = userService.findAll();
        return new ResponseEntity<>(userDtoList, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> findById(@PathVariable Long userId){
        UserDto userDto = userService.findById(userId);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }
}
