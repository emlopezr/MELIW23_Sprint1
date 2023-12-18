package com.example.be_java_hisp_w23_g3.repository.user;

import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private List<User> listOfUsers = new ArrayList<>();
    @Override
    public String followSeller(Long userId, Long userIdToFollow) {
        Seller sellerToFollow = isSeller(userIdToFollow);
        User user = findUserById(userId);
        sellerToFollow.getFollower().add(user);
        user.getFollowing().add(sellerToFollow);
        return "Siguiendo a un nuevo vendedor !";
    }

    public User findUserById(Long userId){
        return listOfUsers.stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    public Seller isSeller(Long userId){
       Seller seller = (Seller) listOfUsers.stream()
               .filter(user -> user.getId().equals(userId))
               .filter(Seller.class :: isInstance)
               .findFirst().orElse(null);
       return seller;
    }
}
