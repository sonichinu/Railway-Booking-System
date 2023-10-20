package com.irctc.User.Controller;

import com.irctc.User.Entity.User;
import com.irctc.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/find/user")
    public List<User> getAllUsers(){
        return this.service.getAllUsers();
    }

//    @GetMapping("/find/user/{id}")
//    public User getSingleUser(@PathVariable int id){
//        return this.service.getSingleUser(id);
//    }

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return this.service.registerUser(user);
    }

    @GetMapping("/find/user/{email}")
    public User getSingleUserByEmail(@PathVariable String email){
        return this.service.getSingleUserByEmail(email);
    }

}
