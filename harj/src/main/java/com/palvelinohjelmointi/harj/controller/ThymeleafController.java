package com.palvelinohjelmointi.harj.controller;

import com.palvelinohjelmointi.harj.entity.Users;
import com.palvelinohjelmointi.harj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ThymeleafController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        // Retrieve the user from the database based on username
        Users user = userRepository.findByUsername(username).orElse(null);

        // If the user exists and the password matches
        if (user != null && user.getPassword().equals(password)) {
            // Successful login, redirect to the home page

            return "redirect:/";
        } else {
            // If authentication fails, redirect to login page or show error message
            return "login"; // Or display an error message like "Invalid username or password"
        }
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
