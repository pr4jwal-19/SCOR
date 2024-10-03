package com.pro.scor.controllers;

import com.pro.scor.entities.User;
import com.pro.scor.helper.LoggedUserHelper;
import com.pro.scor.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class RootController {

    private final Logger logger = LoggerFactory.getLogger(RootController.class);

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedUser(Model model, Authentication authentication){

        // if the user is not authenticated, return
        if (authentication == null){
            return;
        }

        String username = LoggedUserHelper.getEmailOfLoggedInUser(authentication);
        logger.info("Username: {}", username);

        // Fetch user details from the database
        User user = userService.getUserByEmail(username);

        logger.info("User: {}", user);

        model.addAttribute("userLogged", user);
        logger.info(user.getEmail());
        logger.info(user.getSUserName());

    }

}
