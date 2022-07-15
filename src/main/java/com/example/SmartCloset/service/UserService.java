package com.example.SmartCloset.service;

import com.example.SmartCloset.model.User;
import com.example.SmartCloset.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface UserService {

    ArrayList<String> getLikedLooksById(String id);

    ArrayList<String> getLikedClothesById(String id);

    User getUserById(String id);

    User saveOrUpdate(User user);

    void delete(String id);

}
