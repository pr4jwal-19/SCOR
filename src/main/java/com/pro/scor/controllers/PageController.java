package com.pro.scor.controllers;

import com.pro.scor.entities.User;
import com.pro.scor.forms.UserForm;
import com.pro.scor.helper.Message;
import com.pro.scor.helper.MsgType;
import com.pro.scor.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(){
        return "redirect:/home";
    }
    // This is a controller handler method that'll return a view named home
    // for the request URL /home
    @RequestMapping("/home")
    public String home() {
        // should return a view named home
        // templates → home.html
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
    // announcement route
    @RequestMapping("/announcement")
    public String announcement() {
        return "announcement";
    }
    // Login route
    @RequestMapping( "/login")
    public String login() {
        return "login";
    }
    // Signup route
    @RequestMapping("/signup")
    public String signup(Model model) {
        // We will send a blank userForm object to the signup.html(view) page
        UserForm userForm = new UserForm();
        // can send default values too
        //userForm.setUserName("Prajwal Nakure");
        model.addAttribute("userForm", userForm);
        return "signup";
    }

    // After Signup route
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("userForm") UserForm userForm,
                           BindingResult bindingResult, HttpSession session) {
        // fetch the data from the form
        // UserForm → User
        System.out.println(userForm);
        // validate the data
        if (bindingResult.hasErrors()){
            return "signup";
        }

        // if data is valid, then save the data to the database and redirect to login page
        // UserService → saveUser(User user)
        // UserService will have all the business logic for user related operations
        User user = User.builder()
                .sUserName(userForm.getUserName())
                .email(userForm.getEmail())
                .password(userForm.getPassword())
                .phoneNo(userForm.getPhoneNo())
                .about(userForm.getAbout())
                .build();
        User savedUsr = userService.saveUser(user);
        System.out.println(savedUsr);

        // message to the user
        Message msg = Message.builder().
                content("Registration Successful !!")
                .type(MsgType.green)
                .build();
        session.setAttribute("message", msg);

        // else return the user to the signup page

        // return the user to the login page
        return "redirect:/login";
    }

}
