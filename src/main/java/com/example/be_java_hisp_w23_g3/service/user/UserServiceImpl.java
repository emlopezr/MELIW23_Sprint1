package com.example.be_java_hisp_w23_g3.service.user;

import com.example.be_java_hisp_w23_g3.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.exception.NotFoundException;
import com.example.be_java_hisp_w23_g3.repository.seller.SellerRepository;
import com.example.be_java_hisp_w23_g3.repository.user.UserRepository;
import com.example.be_java_hisp_w23_g3.util.DTOMapper;
import com.example.be_java_hisp_w23_g3.dto.response.FollowersListDTO;
import com.example.be_java_hisp_w23_g3.dto.response.UserDTO;
import com.example.be_java_hisp_w23_g3.entity.User;
import com.example.be_java_hisp_w23_g3.util.UserMapper;

import com.example.be_java_hisp_w23_g3.dto.response.FollowedListDTO;
import com.example.be_java_hisp_w23_g3.dto.response.SellerDTO;
import com.example.be_java_hisp_w23_g3.util.SellerMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;

    public UserServiceImpl(UserRepository userRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public FollowersCountDTO getFollowersCount(Long userId) {
        Seller seller = sellerRepository.findSellerById(userId);
        if (seller == null)
            throw new NotFoundException("Seller with id " + userId + " not found");
        return DTOMapper.mapToFollowersCountDTO(seller);
    }

    @Override
    public FollowersListDTO getFollowersList(Long userId) {
        Seller seller = sellerRepository.findSellerById(userId);
        if (seller == null) {
            throw new NotFoundException("Seller with id " + userId + " not found");
        }
        return new FollowersListDTO(userId, seller.getUsername(), mapFollowersToDTO(seller.getFollower()));
    }

    private Set<UserDTO> mapFollowersToDTO(Set<User> followers) {
        return followers.stream().map(UserMapper::mapToDTO).collect(Collectors.toSet());
    }

    @Override
    public FollowedListDTO getFollowedSellersList(Long userID){
        User user = userRepository.findUserByID(userID);
        if(user == null){
            throw new NotFoundException("User with id " + userID + " not found");
        }
        return new FollowedListDTO(user.getId(), user.getUsername(), mapFollowedToDTO(user.getFollowing()));
    }

    public Set<SellerDTO> mapFollowedToDTO(Set<Seller> followed){
        return followed.stream().map(SellerMapper::mapToDTO).collect(Collectors.toSet());
    }

}
