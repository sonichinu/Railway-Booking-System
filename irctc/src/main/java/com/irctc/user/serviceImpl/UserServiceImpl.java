package com.irctc.user.serviceImpl;

import com.irctc.user.entity.User;
import com.irctc.user.repository.UserRepository;
import com.irctc.user.service.UserService;
import com.irctc.exception.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repo;
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public List<User> getAllUsers() {
        return this.repo.findAll();
    }

    @Override
    public User getSingleUser(String userEmail) {
        User user = this.repo.findUserByEmail(userEmail);
        if(user== null){
            throw new ApiException("user not found with user email ",userEmail);
        }
        return user;
    }

    @Override
    public User registerUser(User user) {
        if(this.repo.findUserByEmail(user.getEmail())==null){
            user.setPassword(this.encoder.encode(user.getPassword()));
            return this.repo.save(user);
        }
       throw new ApiException("User Already Exists with this email");
    }

    @Override
    public User updateUser(User user) {
        return this.repo.save(user);
    }

    @Override
    public User getSingleUserByEmail(String email) {
        User user = this.repo.findUserByEmail(email);
        if(user == null){
            throw new ApiException("user not found with user email ",email);
        }
        else{
            return user;
        }
    }

    @Override
    public void deleteUserByEmail(String email) {
        User userToBeDeleted = this.repo.findUserByEmail(email);
        if(userToBeDeleted== null){
            throw new ApiException("User not found with ",email);
        }
        else{
            this.repo.delete(userToBeDeleted);
        }
    }

    @Override
    public List<Object[]> getOnlyUsers() {
        return this.repo.findOnlyUsers();
    }
}
