package com.finalprojectestablishments.finalprojectestablishments.controllers;

import com.finalprojectestablishments.finalprojectestablishments.dao.UserDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.UserDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class UserController {


    private UserDao userDao;
    private UserService clientService;

    @GetMapping("")
    public ResponseEntity<List<User>> getAllClients() {
        List<User> all = userDao.findAll();
        return new ResponseEntity<>(all, HttpStatusCode.valueOf(200));
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getClientById(@PathVariable int id) {
        User client = userDao.findById(id).get();
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveClient(@RequestBody User client) {
        userDao.save(client);
    }
    @PatchMapping("/{id}")
    public void updateClient(@PathVariable int id, @RequestBody UserDto clientDto) {
        User client = userDao.findById(id).get();
        client.setUserName(clientDto.getUserName());
        userDao.save(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable int id) {
        userDao.deleteById(id);

    }

//    @GetMapping("/{clientId}/bookings")
//    public ResponseEntity<List<Booking>> getClientBookings(@PathVariable Long clientId) {
//        List<Booking> bookings = clientService.getClientBookings(clientId);
//        return new ResponseEntity<>(bookings, HttpStatus.OK);
//    }

}