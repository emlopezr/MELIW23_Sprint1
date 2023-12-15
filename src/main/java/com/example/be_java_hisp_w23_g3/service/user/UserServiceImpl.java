package com.example.be_java_hisp_w23_g3.service.user;

import com.example.be_java_hisp_w23_g3.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.exception.NotFoundException;
import com.example.be_java_hisp_w23_g3.repository.user.SellerRepository;
import com.example.be_java_hisp_w23_g3.repository.user.SellerRepositoryImpl;
import com.example.be_java_hisp_w23_g3.repository.user.UserRepository;
import com.example.be_java_hisp_w23_g3.repository.user.UserRepositoryImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;

    public UserServiceImpl(UserRepositoryImpl userRepository, SellerRepositoryImpl sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public FollowersCountDTO getFollowersCount(Long userId) {
        Seller seller = sellerRepository.findSellerById(userId);
        if (seller == null)
            throw new NotFoundException("Seller with id " + userId + " not found");
        return new FollowersCountDTO(seller.getId(), seller.getUsername(), seller.getFollower().size());
    }
}
