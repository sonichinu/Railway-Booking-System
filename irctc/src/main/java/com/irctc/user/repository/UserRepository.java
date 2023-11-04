package com.irctc.user.repository;

import com.irctc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {


    User findUserByEmail(String email);

}
