package com.pro.scor.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    // This is a controller handler method that will return a view named home
    // for the request URL /home
    @RequestMapping("/home")
    public String home(Model model) {
        // should return a view named home
        // templates → home.html
        model.addAttribute("name","Prajwal Nakure");
        model.addAttribute("githubProfile","https://github.com/pr4jwal-19");
        return "home";
    }
    // About route
    @RequestMapping("/about")
    public String about() {
        return "about";
    }
    // Services route
    @RequestMapping("/services")
    public String services() {
        return "services";
    }
    // Contact route
    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }
    // Login route
    @RequestMapping("/login")
    public String login() {
        return "login";
    }
    // Signup route
    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }
}
