package com.irctc.user.repository;

import com.irctc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByEmail(String email);

//    @Query("SELECT u.id, u.email, u.name, u.usersname, u.phone FROM User u WHERE u.email = :email")
//    User findUserByUsername(String email);

}
