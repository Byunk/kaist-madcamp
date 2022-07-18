package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.api.LikeRequest;
import com.example.SmartCloset.model.User;
import com.example.SmartCloset.model.api.LoginRequest;
import com.example.SmartCloset.model.api.SignUpRequest;
import com.example.SmartCloset.service.UserService;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void setResponseHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
    }

    @GetMapping("id")
    public User id(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping("signup")
    public Boolean signup(@RequestBody SignUpRequest signUpRequest) {
        return userService.signUp(signUpRequest);
    }

    @PostMapping("like")
    public Boolean like(@RequestBody LikeRequest likeRequest) {
        return userService.toggleLike(likeRequest);
    }

    @PostMapping("isLiked")
    public Boolean isLiked(@RequestBody LikeRequest likeRequest) {
        return userService.isLike(likeRequest);
    }
/*
    @PostMapping("edit")
    public void edit(@RequestBody SignUpRequest signUpRequest) {
        return userService.saveOrUpdate(user)
    }*/

}
