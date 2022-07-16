package com.example.SmartCloset.service;

import com.example.SmartCloset.controller.UserController;
import com.example.SmartCloset.model.LikeRequest;
import com.example.SmartCloset.model.User;
import com.example.SmartCloset.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ArrayList<String> getLikedLooksById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user != null ? user.get().getLikedCloth() : new ArrayList<>();
    }

    @Override
    public ArrayList<String> getLikedClothesById(String id) {
        User user = userRepository.findById(id).get();
        return user.getLikedCloth();
    }

    @Override
    public Boolean toggleLike(LikeRequest likeRequest) {
        User user = userRepository.findById(likeRequest.getId()).get();

        // Liked List에서 id 탐색

        // toggle

        // Save
        return null;
    }

    @Override
    public Boolean isLike(LikeRequest likeRequest) {
        return null;
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveOrUpdate(User user) { return userRepository.insert(user); }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

}
