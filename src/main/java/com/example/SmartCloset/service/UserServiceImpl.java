package com.example.SmartCloset.service;

import com.example.SmartCloset.controller.UserController;
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
