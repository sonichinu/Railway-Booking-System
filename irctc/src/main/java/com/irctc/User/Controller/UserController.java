package com.irctc.User.Controller;

import com.irctc.User.Entity.User;
import com.irctc.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/find/user")
    public List<User> getAllUsers(){
        return this.service.getAllUsers();
    }
}
