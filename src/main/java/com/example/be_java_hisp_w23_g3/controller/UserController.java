package com.example.be_java_hisp_w23_g3.controller;

import com.example.be_java_hisp_w23_g3.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<?> followSeller(@PathVariable Long userId, @PathVariable Long userIdToFollow){
        return ResponseEntity.ok().body(userService.followSeller(userId, userIdToFollow));
    }
}
