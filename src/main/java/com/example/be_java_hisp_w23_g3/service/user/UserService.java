package com.example.be_java_hisp_w23_g3.service.user;

import com.example.be_java_hisp_w23_g3.dto.response.FollowersCountDTO;

public interface UserService {
    FollowersCountDTO getFollowersCount(Long id);
}
