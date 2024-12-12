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
        // Save the user with the plain-text password (no hashing)
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password); // Save the plain-text password directly
        userRepository.save(user);

        // Redirect to index
        return "redirect:/";
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
}
