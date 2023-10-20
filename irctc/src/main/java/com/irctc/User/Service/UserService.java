package com.irctc.User.Service;

import com.irctc.User.Entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getSingleUser(int id);
    User registerUser(User user);
    User getSingleUserByEmail(String email);
}
