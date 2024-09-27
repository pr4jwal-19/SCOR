package com.pro.scor.repo;

import com.pro.scor.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Interacts with the database
@Repository
public interface UserRepo extends JpaRepository<User,String> {
    // extra methods related to db operations
    // custom methods
    Optional<User> findByEmail(String email);
}
