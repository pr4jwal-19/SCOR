package com.pro.scor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    // User Dashboard
    @GetMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    // User Profile
    @GetMapping("/profile")
    public String userProfile() {
        return "user/profile";
    }

    // Add Notes & Content(links)

    // View Notes & Content(links)

    // Edit Notes & Content(links)

    // Delete Notes & Content(links)

    // Add Social Links

    // View Social Links

    // Edit Social Links

    // Delete Social Links


}
