package com.example.be_java_hisp_w23_g3.repository.user;

import com.example.be_java_hisp_w23_g3.entity.User;

public interface UserRepository {

    User findUserByID(Long userID);

}
