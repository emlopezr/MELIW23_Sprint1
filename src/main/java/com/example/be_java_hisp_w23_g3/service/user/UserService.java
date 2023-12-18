package com.example.be_java_hisp_w23_g3.service.user;

import com.example.be_java_hisp_w23_g3.dto.response.FollowSellerDTO;
import com.example.be_java_hisp_w23_g3.dto.response.FollowersCountDTO;
import com.example.be_java_hisp_w23_g3.dto.response.FollowersListDTO;

import com.example.be_java_hisp_w23_g3.dto.response.FollowedListDTO;

public interface UserService {

    FollowersListDTO getFollowersList(Long userId, String order);

    FollowersCountDTO getFollowersCount(Long id);

    FollowedListDTO getFollowedSellersList(Long userID, String order);

    FollowSellerDTO followSeller(Long userId, Long userIdToFollow);
}
