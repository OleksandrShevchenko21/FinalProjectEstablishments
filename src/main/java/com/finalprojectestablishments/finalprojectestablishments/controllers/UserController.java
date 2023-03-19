package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.UserDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.services.UserService;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor

@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class UserController {


    private UserService userService;
    private UserConverter userConverter;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDto> userDtoList = userConverter.userListToUserDtoList(users);
        return new ResponseEntity<>(userDtoList, HttpStatus.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable int id) {
        User user = userService.findById(id);
        UserDto userDto = userConverter.userToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) {
        userService.save(user);
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserDto clientDto) {
        User user = userService.findById(id);
        user.setUserName(clientDto.getUserName());
        user.setPassword(clientDto.getPassword());
        user.setRole(clientDto.getRole());
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteById(id);

    }
}