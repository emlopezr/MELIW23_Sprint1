package com.example.be_java_hisp_w23_g3.repository.user;

import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.entity.User;

import java.util.Optional;

public interface UserRepository {
    User findUserById(Long userId);

    Optional<User> findUserByIdOptional(Long userId);

    boolean deleteUserById(Long userId);

}