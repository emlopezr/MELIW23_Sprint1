package com.example.be_java_hisp_w23_g3.repository.user;

import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    List<User> users;

    public User findUserByID(Long userID){
        return users.stream().filter(user -> user.getId().equals(userID)).findFirst().orElse(null);
    }

}
