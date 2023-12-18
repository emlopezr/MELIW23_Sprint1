package com.example.be_java_hisp_w23_g3.repository.user;

import com.example.be_java_hisp_w23_g3.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<User> users;

    public UserRepositoryImpl() {
        this.users = new ArrayList<>();
    }

    @Override
    public User findUserById(Long userId) {
        return users.stream()
            .filter(user -> user.getId().equals(userId))
            .findFirst()
            .orElse(null);
    }

    @Override
    public boolean deleteUserById(Long userId) {
        return users.removeIf(user -> user.getId().equals(userId));
    }

    @PostConstruct
    private void load() {
        users.addAll(List.of(
                User.build(1L, "user1"),
                User.build(2L, "user2"),
                User.build(3L, "user3"),
                User.build(4L, "user4"),
                User.build(5L, "user5"),
                User.build(6L, "user6")
        ));
    }

}
