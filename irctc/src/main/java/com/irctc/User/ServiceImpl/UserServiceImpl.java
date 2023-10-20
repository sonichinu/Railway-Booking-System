package com.irctc.User.ServiceImpl;

import com.irctc.User.Entity.User;
import com.irctc.User.Repository.UserRepository;
import com.irctc.User.Service.UserService;
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
        return this.repo.findById(id).get();
    }

    @Override
    public User registerUser(User user) {
        return this.repo.save(user);
    }

    @Override
    public User getSingleUserByEmail(String email) {
        return this.repo.findUserByEmail(email);
    }
}
