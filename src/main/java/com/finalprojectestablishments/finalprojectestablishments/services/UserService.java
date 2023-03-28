package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.UserDao;
import com.finalprojectestablishments.finalprojectestablishments.dto.RestaurantDto;
import com.finalprojectestablishments.finalprojectestablishments.dto.UserDto;
import com.finalprojectestablishments.finalprojectestablishments.entity.Restaurant;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import com.finalprojectestablishments.finalprojectestablishments.utils.converter.UserConverter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
//@NoArgsConstructor
public class UserService {

    private UserDao userDao;
    private UserConverter userConverter;

    public List<User> findAll() {
           return userDao.findAll();
    }

    public User findById(int id) {
       return userDao.findById(id).get();
    }
    public User findByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }
    public void save (User user){
        userDao.save(user);
    }

    public void update(User user) {
        userDao.save(user);
    }

    public void deleteByUserName(String userName) {
        userDao.removeUserByUserName(userName);
    }

    public User findByName(String userName) {
        return userDao.findUserByUserName(userName);
    }
    public List<User> findListByUserName(String userName) {

        return userDao.findUsersByUserName(userName);
    }
}


