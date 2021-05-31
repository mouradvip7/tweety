package com.example.tweety.repo;

import com.example.tweety.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByScreenName(String screenName);
}