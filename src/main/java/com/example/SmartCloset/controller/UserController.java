package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.LikeRequest;
import com.example.SmartCloset.model.User;
import com.example.SmartCloset.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("id")
    @ResponseBody
    public User id(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @PostMapping(value="save")
    @ResponseBody
    public User save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    // TODO: 2022/07/15 like & isLike Method & login & signup

    @PostMapping("like")
    @ResponseBody
    public Boolean like(@RequestBody LikeRequest likeRequest) {
        logger.info("id:" + likeRequest.getId());
        logger.info("cloth id:" + likeRequest.getClothId());
        logger.info("look id:" + likeRequest.getLookId());
        logger.info("user id:" + likeRequest.getUserId());
        return true;
    }

    @PostMapping("isLiked")
    @ResponseBody
    public Boolean isLiked(@RequestBody LikeRequest likeRequest) {
        return null;
    }

    @GetMapping("")
    @ResponseBody
    public String test() {
        return "test successful!";
    }
}
