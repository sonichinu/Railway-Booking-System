package com.irctc.user.controller;

import com.irctc.user.entity.User;
import com.irctc.user.service.UserService;
import com.irctc.exception.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping("/find/user")
    public ResponseEntity<List<User>> getalluser()
    {
        return ResponseEntity.ok(service.getAllUsers());
    }

//    @GetMapping("/find/user/{id}")
//    public User getSingleUser(@PathVariable int id){
//        return this.service.getSingleUser(id);
//    }

    @PostMapping("/register")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<ApiResponse> user(@Valid @RequestBody User user){
        this.service.registerUser(user);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user added",true), HttpStatus.OK);
    }

//    @GetMapping("/find/user/{email}")
//    public User getSingleUserByEmail(@PathVariable String email){
//        return this.service.getSingleUserByEmail(email);
//    }

    @PutMapping("/update")
    @CrossOrigin("http://localhost:4200")
    public ResponseEntity<ApiResponse> updateUser(@Valid @RequestBody User user){
        this.service.updateUser(user);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user updated Successfully",true), HttpStatus.OK);
    }

}
