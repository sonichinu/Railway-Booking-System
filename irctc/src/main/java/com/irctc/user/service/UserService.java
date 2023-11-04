package com.irctc.user.service;

import com.irctc.user.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getSingleUser(String userEmail);
    User registerUser(User user);
    User updateUser(User user);
//    User getSingleUserByEmail(String email);

}
