package com.example.be_java_hisp_w23_g3.controller;

import com.example.be_java_hisp_w23_g3.service.user.UserService;
import com.example.be_java_hisp_w23_g3.service.user.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users/{userID}/followed/list")
    public ResponseEntity<?> getFollowedSellerList(@PathVariable Long userID){
        return new ResponseEntity<>(userService.getFollowedSellersList(userID), HttpStatus.OK);
    }
}
