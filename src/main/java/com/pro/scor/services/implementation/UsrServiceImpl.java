package com.pro.scor.services.implementation;

import com.pro.scor.entities.User;
import com.pro.scor.helper.ResourceNotFoundException;
import com.pro.scor.repo.UserRepo;
import com.pro.scor.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsrServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // before saving the user, userId has to be generated automatically
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);
        logger.info("Saving user with phoneNo: {}", user.getPhoneNo());
        return userRepo.save(user);
    }

    @Override
    public Optional<User> getUserById(String userId) {
        return userRepo.findById(userId);
    }

    @Override
    public Optional<User> updateUser(User user) {
        User userForUpdate = userRepo.findById(user.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        // update the userForUpdate object with the new values from the user object
        userForUpdate.setUserName(user.getUserName());
        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setPassword(user.getPassword());
        userForUpdate.setAbout(user.getAbout());
        userForUpdate.setPhoneNo(user.getPhoneNo());
        userForUpdate.setProfilePicLink(user.getProfilePicLink());
        userForUpdate.setEmailVerified(user.isEmailVerified());
        userForUpdate.setPhoneVerified(user.isPhoneVerified());
        userForUpdate.setUserVerified(user.isUserVerified());
        userForUpdate.setProvider(user.getProvider());
        userForUpdate.setProviderUsrId(user.getProviderUsrId());

        User save = userRepo.save(userForUpdate);
        return Optional.of(save);
    }

    @Override
    public void deleteUser(String userId) {
        User userForUpdate = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        userRepo.delete(userForUpdate);
    }

    @Override
    public boolean isUserPresent(String userId) {
        return userRepo.existsById(userId);
    }

    @Override
    public boolean isEmailPresent(String email) {
        return userRepo.findByEmail(email).isPresent();
    }

    @Override
    public Optional<List<User>> getAllUsers() {
        return Optional.empty();
    }
}
