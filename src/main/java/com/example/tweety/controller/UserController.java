package com.example.tweety.controller;

import com.example.tweety.model.User;
import com.example.tweety.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{screenName}")
    public Mono<User> getUserByScreenName(@PathVariable String
                                                  screenName) {
        return userService.getUserByScreenName(screenName);
    }

    @PutMapping("/{userId}/follow")
    @ResponseStatus(code = HttpStatus.OK)
    public void followUser(Principal principal, @PathVariable String
            userId) {
        Mono<User> user =
                userService.getUserByScreenName(principal.getName());
        user.subscribe(u -> {
            if (!u.getUserId().equalsIgnoreCase(userId)) {
                u.getFollowing().add(userId);
                userService.save(u);
            }

        });
    }
}