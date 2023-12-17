package com.example.be_java_hisp_w23_g3.service.user;

import com.example.be_java_hisp_w23_g3.dto.response.FollowedListDTO;
import com.example.be_java_hisp_w23_g3.dto.response.SellerDTO;
import com.example.be_java_hisp_w23_g3.entity.Seller;
import com.example.be_java_hisp_w23_g3.entity.User;
import com.example.be_java_hisp_w23_g3.exception.NotFoundException;
import com.example.be_java_hisp_w23_g3.repository.user.UserRepositoryImpl;
import com.example.be_java_hisp_w23_g3.util.SellerMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepositoryImpl userRepository;

    public UserServiceImpl(UserRepositoryImpl userRepository){
        this.userRepository = userRepository;
    }

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
