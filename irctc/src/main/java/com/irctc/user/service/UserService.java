package com.irctc.user.service;

import com.irctc.user.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getSingleUser(int id);
    User registerUser(User user);
    User getSingleUserByEmail(String email);

}
