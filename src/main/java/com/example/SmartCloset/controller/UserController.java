package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.api.LikeRequest;
import com.example.SmartCloset.model.User;
import com.example.SmartCloset.model.api.LoginRequest;
import com.example.SmartCloset.model.api.SignUpRequest;
import com.example.SmartCloset.service.UserService;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping(value = "like/images", produces = MeidaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> fetchImages(@RequestParam String id) {
        ArrayList<byte[]> result = new ArrayList<>();
        List<String> urls = userService.likeImageUrls(id);
        
        String curWorkingDir = System.getProperty("user.dir");
        for (String url: urls) {
            String path = curWorkingDir + "/src/main/resources/static/save_image/";
            InputStream imageStream = new FileInputStream(path + url);
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            imageStream.close();
            result.add(imageByteArray);
        }
        return new ResponseEntity<ArrayList<byte[]>(result, HttpStatus.OK);
    }*/


/*
    @PostMapping("edit")
    public void edit(@RequestBody SignUpRequest signUpRequest) {
        return userService.saveOrUpdate(user)
    }*/

}
