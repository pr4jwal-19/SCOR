package com.pro.scor.services;

import com.pro.scor.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(String userId);
    Optional<User> updateUser(User user);
    void deleteUser(String userId);
    boolean isUserPresent(String userId);
    boolean isEmailPresent(String email);
    Optional<List<User>> getAllUsers();
    User getUserByEmail(String email);
    // Add more methods as per requirement

}
