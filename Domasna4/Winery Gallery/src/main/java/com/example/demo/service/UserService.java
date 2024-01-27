package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.model.Role;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.List;
import java.util.Optional;

public interface UserService  {
    User register(String username, String password, String repeatPassword, String email, Role role);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    User update(User user);

    List<User> findAll();




    boolean isUserInList(String username, List<User> userList);

    public UserDetails loadUserByUsername(String username);
}
