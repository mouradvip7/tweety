package com.example.tweety;

import com.example.tweety.model.Role;
import com.example.tweety.model.Tweet;
import com.example.tweety.model.User;
import com.example.tweety.service.TweetService;
import com.example.tweety.service.UserService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.UUID;

@SpringBootApplication
public class TweetyApplication {

    public static void main(String[] args) {
        SpringApplication.run(TweetyApplication.class, args);
    }


    @Bean
    public ApplicationRunner applicationRunner(UserService userService, TweetService tweetService) {
        return args -> {
            userService.save(new User(UUID.randomUUID().toString(), "{noop}abc123", "shazin", Role.ADMIN, null, "/assets/images/5rgcB.jpg", null)).subscribe(user -> {
                tweetService.save(new Tweet(null, null, user, "Hi This is a Tweet")).subscribe();
            });

            userService.save(new User(UUID.randomUUID().toString(), "{noop}abc123", "shahim", Role.ADMIN, null, "/assets/images/BNhr3.jpg", null)).subscribe(user -> {
                tweetService.save(new Tweet(null, null, user, "Hi @shazin")).subscribe();
            });

        };
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
