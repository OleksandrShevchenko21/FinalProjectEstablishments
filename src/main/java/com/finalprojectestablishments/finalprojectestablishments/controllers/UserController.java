package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.UserDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.services.RestaurantService;
import com.finalprojectestablishments.finalprojectestablishments.services.UserService;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.UserConverter;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor

//@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
public class UserController {


    private UserService userService;
    private UserConverter userConverter;

    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDto> userDtoList = userConverter.userListToUserDtoList(users);
//        List<UserDto> users =userService.findAll();

        return new ResponseEntity<>(userDtoList, HttpStatus.valueOf(200));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getOneUser(@PathVariable int id) {
        User user = userService.findById(id);
        UserDto userDto = userConverter.userToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.valueOf(200));
    }
    @GetMapping("/username/{userName}")
    public ResponseEntity<UserDto> getUserbyUserName(@PathVariable String userName) {
        User user = userService.findByName(userName);
        UserDto userDto = userConverter.userToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.valueOf(200));
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
//        user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole("ROLE_" + userDto.getRole());
        userService.save(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody UserDto userDto){
        Authentication authenticate =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                        (userDto.getUserName(), userDto.getPassword()));

        if(authenticate != null){

            String jwtToken = Jwts.builder()
                    .setSubject(authenticate.getName())
//                    .setRole(authenticate.getRole())
                    .setExpiration(new Date(new Date().getTime() + 60 * 60 * 1000))
                    .signWith(SignatureAlgorithm.HS512, "gfl".getBytes(StandardCharsets.UTF_8))
                    .compact();
            System.out.println(jwtToken);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization","Bearer " +jwtToken);
            return new ResponseEntity<>("you are log in", headers, HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PatchMapping("/{id}")
    public void updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        User user = userService.findById(id);
        user.setUserName(userDto.getUserName());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        userService.update(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteById(id);

    }

}