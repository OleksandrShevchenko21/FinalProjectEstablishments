package com.finalprojectestablishments.finalprojectestablishments.dao;

import com.finalprojectestablishments.finalprojectestablishments.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, Integer> {
    User findUserByUserName(String username);
    String findByUserName(String username);

    List<User> findUsersByUserName(String userName);

    void removeUserByUserName(String userName);
}
