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
            throw new ApiException("user not found");
        }
        return user;
    }

    @Override
    public User registerUser(User user) {
        user.setPassword(this.encoder.encode(user.getPassword()));
        return this.repo.save(user);
    }

    @Override
    public User updateUser(User user) {
//        user.setPassword(
//                getSingleUserByEmail(user.getEmail())
//                        .getPassword()
//        );
//        this.repo.find
        return this.repo.save(user);
    }

//    @Override
//    public User getSingleUserByEmail(String email) {
//        User user = this.repo.findUserByEmail(email);
//        if(user == null){
//            throw new ApiException("user not found");
//        }
//        else{
//            return user;
//        }
//    }
}
