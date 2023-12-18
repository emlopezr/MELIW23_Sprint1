package com.example.be_java_hisp_w23_g3.repository.user;

import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.entity.User;

public interface UserRepository {
    String followSeller(Long userId, Long userIdToFollow);

    Seller isSeller(Long userIdToFollow);

    User findUserById(Long userId);
}
