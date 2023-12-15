package com.example.be_java_hisp_w23_g3.entity;

import java.util.HashSet;
import java.util.Set;

public class User {
    private final Long id;
    private String username;
    private Set<Seller> following;

    public User(Long id, String username) {
        this.id = id;
        this.username = username;
        this.following = new HashSet<>();
    }

    public User(Long id, String username, Set<Seller> following) {
        this.id = id;
        this.username = username;
        this.following = following;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Set<Seller> getFollowing() {
        return following;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFollowing(Set<Seller> following) {
        this.following = following;
    }
}
