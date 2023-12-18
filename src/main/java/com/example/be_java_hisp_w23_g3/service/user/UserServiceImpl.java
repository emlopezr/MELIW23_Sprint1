package com.example.be_java_hisp_w23_g3.service.user;

import com.example.be_java_hisp_w23_g3.dto.response.FollowersListDTO;
import com.example.be_java_hisp_w23_g3.dto.response.UserDTO;
import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.entity.User;
import com.example.be_java_hisp_w23_g3.exception.NotFoundException;
import com.example.be_java_hisp_w23_g3.repository.seller.SellerRepository;
import com.example.be_java_hisp_w23_g3.repository.user.UserRepository;
import com.example.be_java_hisp_w23_g3.util.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;

    public UserServiceImpl(UserRepository userRepository, SellerRepository sellerRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
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
}
