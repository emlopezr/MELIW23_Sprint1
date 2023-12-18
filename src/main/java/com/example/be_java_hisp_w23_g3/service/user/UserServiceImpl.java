package com.example.be_java_hisp_w23_g3.service.user;

import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.entity.User;
import com.example.be_java_hisp_w23_g3.exception.UserNotFoundException;
import com.example.be_java_hisp_w23_g3.repository.seller.SellerRepository;
import com.example.be_java_hisp_w23_g3.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;

    public UserServiceImpl(UserRepository userRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    public String followSeller(Long userId, Long userIdToFollow) {
        Seller sellerToFollow = sellerRepository.findSellerById(userIdToFollow);
        User user = userRepository.findUserById(userId);
        if(sellerToFollow == null){
            throw new UserNotFoundException("El vendedor elegido no existe");
        }else if(user == null){
            throw new UserNotFoundException("El usuario no existe");
        }
        sellerToFollow.getFollower().add(user);
        user.getFollowing().add(sellerToFollow);
        return userRepository.followSeller(userId, userIdToFollow);
    }
}


