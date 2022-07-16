package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.LikeRequest;
import com.example.SmartCloset.model.User;
import com.example.SmartCloset.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("id")
    public User id(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @PostMapping(value="save")
    public User save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    // TODO: 2022/07/15 like & isLike Method & login & signup

    @PostMapping("like")
    public Boolean like(@RequestBody LikeRequest likeRequest) {

        logger.info("id:" + likeRequest.getId());
        logger.info("cloth id:" + likeRequest.getClothId());
        logger.info("look id:" + likeRequest.getLookId());
        logger.info("user id:" + likeRequest.getUserId());
        return userService.toggleLike(likeRequest);
    }

    @PostMapping("isLiked")
    public Boolean isLiked(@RequestBody LikeRequest likeRequest) {
        return userService.isLike(likeRequest);
    }

}
