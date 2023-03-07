package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.UserDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.services.UserService;
import com.finalprojectestablishments.finalprojectestablishments.utils.UserConverter;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {


    private UserService userService;
    private UserConverter userConverter;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllRestaurants() {
        List<User> users = userService.findAll();
        List<UserDto> userDtoList = userConverter.userListToUserDtoList(users);
        return new ResponseEntity<>(userDtoList, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable int id) {
        User user = userService.findById(id);
        UserDto userDto = userConverter.userToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatusCode.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@RequestBody User user) {
        userService.save(user);
    }

    @PatchMapping("/{id}")
    public void updateClient(@PathVariable int id, @RequestBody UserDto clientDto) {
        User client = userService.findById(id);
        client.setUserName(clientDto.getUserName());
        userService.update(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        userService.deleteById(id);

    }

}