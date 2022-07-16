package com.example.SmartCloset.service;

import com.example.SmartCloset.model.api.LikeRequest;
import com.example.SmartCloset.model.User;

import java.util.ArrayList;

public interface UserService {

    ArrayList<String> getLikedLooksById(String id);

    ArrayList<String> getLikedClothesById(String id);

    Boolean toggleLike(LikeRequest likeRequest);

    Boolean isLike(LikeRequest likeRequest);

    Long countUser();

    User getUserById(String id);

    User saveOrUpdate(User user);

    void delete(String id);

}
