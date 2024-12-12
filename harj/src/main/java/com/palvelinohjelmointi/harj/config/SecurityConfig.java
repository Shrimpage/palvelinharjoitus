package com.palvelinohjelmointi.harj.config;

import com.palvelinohjelmointi.harj.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/login", "/css/**", "/js/**", "/h2-console/**").permitAll() // Allow access to login, static resources, and H2 console
                .anyRequest().authenticated() // All other routes require authentication
                .and()
                .formLogin()
                .loginPage("/login") // Custom login page URL
                .permitAll() // Allow all users to access the login page
                .and()
                .logout()
                .permitAll() // Allow all users to log out
                .and()
                .csrf().disable() // Disable CSRF for the H2 console (required)
                .headers().frameOptions().sameOrigin(); // Allow frames from the same origin (necessary for H2 console)

        return http.build();
    }

    // No password encoder bean since we are using plain-text passwords

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }
}
