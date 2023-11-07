package com.irctc.user.repository;

import com.irctc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByEmail(String email);

    @Query("SELECT u.id, u.name, u.usersname, u.email, u.phone FROM User u WHERE u.role = null")
    List<Object[]> findOnlyUsers();

}
