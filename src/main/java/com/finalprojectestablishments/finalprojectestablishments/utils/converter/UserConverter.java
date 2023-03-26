package com.finalprojectestablishments.finalprojectestablishments.utils.converter;

import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.UserDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class UserConverter {

    public UserDto userToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setPassword(user.getPassword());
        dto.setRole(user.getRole());
        dto.setRole(user.getRole());
        dto.setNumber(user.getNumber());
        dto.setEmail(user.getEmail());

        return dto;
    }

    public List<UserDto> userListToUserDtoList(List<User> userList) {
        return userList.stream().map(this::userToUserDto).collect(Collectors.toList());
    }
}
