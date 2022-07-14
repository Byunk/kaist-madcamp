package com.example.SmartCloset.service;

import com.example.SmartCloset.model.User;
import com.example.SmartCloset.repository.UserRepository;

import java.util.List;
import java.util.Optional;

public interface UserService {

    String getAll();

    User getUserById(String id);

    User saveOrUpdate(User user);

    void delete(String id);

}
