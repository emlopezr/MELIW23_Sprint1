package com.example.be_java_hisp_w23_g3.service.user;

import com.example.be_java_hisp_w23_g3.dto.response.FollowersListDTO;

public interface UserService {

    FollowersListDTO getFollowersList(Long userId);
    FollowersCountDTO getFollowersCount(Long id);
}
