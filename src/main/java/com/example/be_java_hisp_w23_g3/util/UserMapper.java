package com.example.be_java_hisp_w23_g3.util;

import com.example.be_java_hisp_w23_g3.dto.response.UserDTO;
import com.example.be_java_hisp_w23_g3.entity.User;

public class UserMapper {

    public static UserDTO mapToDTO(User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }
}
