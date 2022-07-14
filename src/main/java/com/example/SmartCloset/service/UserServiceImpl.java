package com.example.SmartCloset.service;

import com.example.SmartCloset.controller.UserController;
import com.example.SmartCloset.model.User;
import com.example.SmartCloset.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return "user";
        //return users;
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
