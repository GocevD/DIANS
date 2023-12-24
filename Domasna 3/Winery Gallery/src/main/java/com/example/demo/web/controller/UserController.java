package com.example.demo.web.controller;

import com.example.demo.model.User;
import com.example.demo.model.Wine;
import com.example.demo.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/user")
    public String userProfile(Model model, Authentication authentication, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (authentication != null && user == null) {
            String username = authentication.getName();
            user = userService.findByUsername(username);
            request.getSession().setAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("searchUser", "");
        return "user";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        // Assuming you have a method to save the user in your UserRepository
        userService.register(user.getUsername(), user.getPassword(), user.getPassword(), user.getEmail(), user.getRole());
        return "redirect:/login"; // Redirect to the login page after successful registration
    }

//(String username, String password, String repeatPassword, String email, Role role)










}