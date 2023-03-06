package com.finalprojectestablishments.finalprojectestablishments.services;

import com.finalprojectestablishments.finalprojectestablishments.dao.UserDao;
import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
//@NoArgsConstructor
public class UserService {


    private UserDao userDao;



    public void save (User user){
        userDao.save(user);
    }

    public User findById(int id) {
       return userDao.findById(id).get();

    }

}
