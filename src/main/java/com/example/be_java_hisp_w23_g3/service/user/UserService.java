package com.example.be_java_hisp_w23_g3.service.user;

import com.example.be_java_hisp_w23_g3.dto.response.FollowedListDTO;

public interface UserService {
    FollowedListDTO getFollowedSellersList(Long userID);
}
