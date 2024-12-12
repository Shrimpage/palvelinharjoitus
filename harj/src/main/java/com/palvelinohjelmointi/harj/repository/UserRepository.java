package com.palvelinohjelmointi.harj.repository;

import com.palvelinohjelmointi.harj.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    // Custom query method to find a user by username
    Optional<Users> findByUsername(String username);
}
