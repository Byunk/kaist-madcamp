package com.example.SmartCloset.service;

import com.example.SmartCloset.model.api.LikeRequest;
import com.example.SmartCloset.model.Look;
import com.example.SmartCloset.model.User;
import com.example.SmartCloset.model.api.LoginRequest;
import com.example.SmartCloset.model.api.SignUpRequest;
import com.example.SmartCloset.model.api.exception.UserNotFoundException;
import com.example.SmartCloset.model.api.exception.ErrorCode;
import com.example.SmartCloset.model.api.exception.IdDuplicatedException;
import com.example.SmartCloset.model.api.exception.InvalidInputException;
import com.example.SmartCloset.model.api.exception.PasswordInvalidException;
import com.example.SmartCloset.repository.LookRepository;
import com.example.SmartCloset.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private LookRepository lookRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, LookRepository lookRepository) {
        this.userRepository = userRepository;
        this.lookRepository = lookRepository;
    }

    @Override
    public Boolean toggleLike(LikeRequest likeRequest){
        User user = userRepository.findById(likeRequest.getId()).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User Not Found", ErrorCode.USER_NOT_FOUND);
        }

        String targetId = null;

        switch (likeRequest.getRequestType()) {
            /*
            case CLOTH -> {
                targetId = likeRequest.getClothId();
                ArrayList<String> likedClothes = user.getLikedUser() == null ? new ArrayList() : user.getLikedCloth();
                if (likedClothes == null) {
                    return null;
                }

                if (likedClothes.contains(targetId)) {
                    likedClothes.remove(targetId);
                    user.setLikedCloth(likedClothes);
                    userRepository.save(user);
                    return false;
                } else {
                    likedClothes.add(targetId);
                    user.setLikedCloth(likedClothes);
                    userRepository.save(user);
                    return true;
                }
            }*/
            case LOOK -> {
                targetId = likeRequest.getLookId();
                ArrayList<String> likedLooks = user.getLikedLook();
                if (likedLooks == null) {
                    likedLooks = new ArrayList<>();
                }

                // Valid Check
                Look look = lookRepository.getLookById(targetId);
                if (look == null) {
                    throw new InvalidInputException("No Look in DB", ErrorCode.INVALID_INPUT);
                }

                if (likedLooks.contains(targetId)) {
                    likedLooks.remove(targetId);
                    user.setLikedLook(likedLooks);
                    userRepository.save(user);
                    return false;
                } else {
                    likedLooks.add(targetId);
                    user.setLikedLook(likedLooks);
                    userRepository.save(user);
                    return true;
                }
            }
            case USER -> {
                targetId = likeRequest.getUserId();
                ArrayList<String> likedUser = user.getLikedUser() == null ? 
                    new ArrayList<String>() : user.getLikedUser();

                if (likedUser.contains(targetId)) {
                    likedUser.remove(targetId);
                    user.setLikedUser(likedUser);
                    userRepository.save(user);
                    return false;
                } else {
                    likedUser.add(targetId);
                    user.setLikedUser(likedUser);
                    userRepository.save(user);
                    return true;
                }
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public Boolean isLike(LikeRequest likeRequest) {
        User user = userRepository.findById(likeRequest.getId()).get();
        String targetId = null;

        switch (likeRequest.getRequestType()) {
            /*
            case CLOTH -> {
                targetId = likeRequest.getClothId();
                return user.getLikedCloth().contains(targetId);
            }*/
            case LOOK -> {
                targetId = likeRequest.getLookId();
                return user.getLikedLook().contains(targetId);
            }
            case USER -> {
                targetId = likeRequest.getUserId();
                return user.getLikedUser().contains(targetId);
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public Long countUser() {
        return userRepository.count();
    }

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByLoginId(loginRequest.getId()).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User Not Found", ErrorCode.USER_NOT_FOUND);
        }

        if (user.getPw().equals(loginRequest.getPw())) {
            System.out.println("????????? ??????");
            return user.getUserId();
        } else {
            throw new PasswordInvalidException("Password Invalid", ErrorCode.PW_INVALID);
        }
    }

    @Override
    public void Edit(SignUpRequest signUpRequest) {
       //User uesr = userRepository.findById(id)
    }

    @Override
    public Boolean signUp(SignUpRequest signUpRequest) {
        User user = userRepository.findByLoginId(signUpRequest.getId()).orElse(null);
        if (user != null) {
            throw new IdDuplicatedException("ID Duplicated", ErrorCode.ID_DUPLICATED);
        } else {
            saveOrUpdate(new User(signUpRequest.getId(), signUpRequest.getPw(), signUpRequest.getUsername()));
            return true;
        }
    }

    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User Not Found", ErrorCode.USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    public User saveOrUpdate(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<String> likeImageUrls(String id) {
        ArrayList<String> result = new ArrayList<>();
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User Not Found", ErrorCode.USER_NOT_FOUND);
        }

        List<String> lookIds = (List<String>) user.getLikedLook();
        log.error(lookIds.toString());

        for (String lookId: lookIds) {
            Look look = lookRepository.getLookById(lookId);
            if (look == null) {
                throw new InvalidInputException("Look Not Found with " + lookId, ErrorCode.INVALID_INPUT);
            }
            result.add(look.getImgUrl());
        }
        return result;
    }

    @Override
    public User findUserWithLookId(String id) {
        List<User> users = userRepository.findAll();
        for (User user: users) {
            if (user.getUploadLook().contains(id)) {
                return user;
            }
        }
        throw new InvalidInputException("Invalid look Id", ErrorCode.INVALID_INPUT);
    }

    @Override
    public void deleteAll(String id) {
        User user = getUserById(id);
        ArrayList<String> lookIds = user.getUploadLook();
        
        for (String lookId: lookIds) {
            lookRepository.deleteById(lookId);
            lookIds.remove(lookId);
        }
        user.setUploadLook(new ArrayList<>());
        saveOrUpdate(user);
    }

}

