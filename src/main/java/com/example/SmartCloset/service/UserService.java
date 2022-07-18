package com.example.SmartCloset.service;

import com.example.SmartCloset.model.api.LikeRequest;
import com.example.SmartCloset.model.User;
import com.example.SmartCloset.model.api.LoginRequest;
import com.example.SmartCloset.model.api.SignUpRequest;

import java.util.ArrayList;

public interface UserService {

    Boolean toggleLike(LikeRequest likeRequest);

    Boolean isLike(LikeRequest likeRequest);

    Long countUser();

    void Edit(SignUpRequest signUpRequest);

    String login(LoginRequest loginRequest);

    Boolean signUp(SignUpRequest signUpRequest);

    User getUserById(String id);

    User saveOrUpdate(User user);

    void delete(String id);

}
