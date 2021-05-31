package com.example.tweety.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @NotNull
    private String userId;
    @JsonIgnore
    @NotNull
    private String password;
    @NotNull
    @Column(unique = true)
    private String screenName;
    @NotNull
    private Role role;
    private String bio;
    private String profileImage;
    @ElementCollection
    private Set<String> following;
}