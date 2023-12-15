package com.example.be_java_hisp_w23_g3.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Seller extends User {
    private Set<User> follower;
    private Map<Integer, Post> posts;

    public Seller(Long id, String username) {
        super(id, username);
        this.follower = new HashSet<>();
        this.posts = new HashMap<>();
    }

    public Seller(Long id, String username, Set<Seller> following, Set<User> follower, Map<Integer, Post> posts) {
        super(id, username, following);
        this.follower = follower;
        this.posts = posts;
    }

    public Set<User> getFollower() {
        return follower;
    }

    public Map<Integer, Post> getPosts() {
        return posts;
    }

    public void setFollower(Set<User> follower) {
        this.follower = follower;
    }

    public void setPosts(Map<Integer, Post> posts) {
        this.posts = posts;
    }
}
