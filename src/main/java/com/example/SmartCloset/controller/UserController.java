package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.User;
import com.example.SmartCloset.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id")
    public User id(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @PostMapping(value="save")
    @ResponseBody
    public User save(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }

    @GetMapping("")
    @ResponseBody
    public String test() {
        return "test successful!";
    }
}
