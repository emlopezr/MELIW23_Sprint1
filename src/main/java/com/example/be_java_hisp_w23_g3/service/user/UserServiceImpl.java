package com.example.be_java_hisp_w23_g3.service.user;

import com.example.be_java_hisp_w23_g3.dto.response.FollowSellerDTO;
import com.example.be_java_hisp_w23_g3.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.exception.NotFoundException;
import com.example.be_java_hisp_w23_g3.repository.seller.SellerRepository;
import com.example.be_java_hisp_w23_g3.repository.user.UserRepository;
import com.example.be_java_hisp_w23_g3.util.DTOMapper;
import com.example.be_java_hisp_w23_g3.dto.response.FollowersListDTO;
import com.example.be_java_hisp_w23_g3.entity.User;
import com.example.be_java_hisp_w23_g3.util.UserMapper;

import com.example.be_java_hisp_w23_g3.dto.response.FollowedListDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;

    public UserServiceImpl(UserRepository userRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public FollowersCountDTO getFollowersCount(Long userId) {
        Seller seller = sellerRepository.findSellerByIdOptional(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found"));
        return DTOMapper.mapToFollowersCountDTO(seller);
    }

    @Override
    public FollowersListDTO getFollowersList(Long userId, String order) {
        Seller seller = sellerRepository.findSellerByIdOptional(userId)
                .orElseThrow(() -> new NotFoundException("User with id " + userId + " not found"));

        return UserMapper.mapToFollowersListDTO(seller, order);
    }

    @Override
    public FollowedListDTO getFollowedSellersList(Long userID, String order) {
        User user = userRepository.findUserByIdOptional(userID)
                .or(() -> sellerRepository.findSellerByIdOptional(userID))
                .orElseThrow(() -> new NotFoundException("User with id " + userID + " not found"));

        return UserMapper.mapToFollowedListDTO(user, order);
    }

    public FollowSellerDTO followSeller(Long userId, Long userIdToFollow) {
        Seller sellerToFollow = sellerRepository.findSellerById(userIdToFollow);
        User user = userRepository.findUserById(userId);
        if(sellerToFollow == null){
            throw new NotFoundException("Seller with id " + userIdToFollow + " not found");
        }else if(user == null){
            throw new NotFoundException("User with id " + userId + " not found");
        }
        sellerToFollow.getFollower().add(user);
        user.getFollowing().add(sellerToFollow);
        return new FollowSellerDTO("Following a new Seller!");
    }
}


