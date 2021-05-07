package com.jwt.repo;

import com.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// to send user date to database(save to database using JPA)
public interface UserRepository extends JpaRepository<User, Long> {
    // username, it will return the user of given username
    public User findByUsername(String username);
}
