package com.irctc.user.serviceImpl;

import com.irctc.user.entity.User;
import com.irctc.user.repository.UserRepository;
import com.irctc.user.service.UserService;
import com.irctc.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repo;


    @Override
    public List<User> getAllUsers() {
        return this.repo.findAll();
    }

    @Override
    public User getSingleUser(int id) {
        return this.repo.findById(id).orElseThrow(()-> new ApiException("user not found"));
    }

    @Override
    public User registerUser(User user) {
        return this.repo.save(user);
    }

    @Override
    public User getSingleUserByEmail(String email) {
        User user = this.repo.findUserByEmail(email);
        if(user == null){
            throw new ApiException("user not found");
        }
        else{
            return user;
        }
    }
}
