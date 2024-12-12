package com.palvelinohjelmointi.harj.service;

import com.palvelinohjelmointi.harj.entity.Users;
import com.palvelinohjelmointi.harj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService; // Make sure to import this interface
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Retrieve the user from the database as an Optional
        Optional<Users> optionalUser = userRepository.findByUsername(username);

        // Handle the case where the user is not found
        Users user = optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Return the user as UserDetails with plain-text password
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // Plain-text password
                .roles("USER") // Adjust roles as needed
                .build();
    }
}
