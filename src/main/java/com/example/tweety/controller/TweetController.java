package com.example.tweety.controller;


import com.example.tweety.model.Tweet;
import com.example.tweety.model.User;
import com.example.tweety.service.TweetService;
import com.example.tweety.service.UserService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
@RequestMapping("/tweets")
public class TweetController {
    private final TweetService tweetService;
    private final UserService userService;

    public TweetController(TweetService tweetService, UserService
            userService) {
        this.tweetService = tweetService;
        this.userService = userService;
    }

    @PostMapping
    public Mono<Tweet> save(Principal principal, @RequestBody Tweet
            tweet) {
        Mono<User> user =
                userService.getUserByScreenName(principal.getName());
        return user.flatMap(u -> {
            tweet.setTweetUser(u);
            return tweetService.save(tweet);
        });
    }

    @GetMapping
    public Flux<Tweet> getAll(Principal principal) {
        return tweetService.getRelevantTweets(principal.getName());
    }
}