package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.*;
import com.example.SmartCloset.service.ClothService;
import com.example.SmartCloset.service.LookService;
import com.example.SmartCloset.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("look")
public class LookController {

    private final LookService lookService;
    private final ClothService clothService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public LookController(LookService lookService, ClothService clothService, UserService userService) {
        this.lookService = lookService;
        this.clothService = clothService;
        this.userService = userService;
    }

    @GetMapping("id")
    @ResponseBody
    public Look id(@RequestParam String id) {
        return lookService.getLookById(id);
    }

    @PostMapping("search")
    @ResponseBody
    public SearchResponse search(@RequestParam Request request) {
        // Get User
        User user = userService.getUserById(request.getUserId());

        // Get Liked Clothes List
        ArrayList<Cloth> likedClothes = clothService.findClothesById(user.getLikedCloth());
        //ArrayList<Look> likedLooks = lookService.findLooksById(user.getLikedLooks());

        // Calculate Inclination
        Inclination inclination = new Inclination();
        inclination.setColorDistribution(clothService.getColorDistribution(likedClothes));
        //inclination.setTpoDistribution(lookService.getTPODistribution(likedLooks));

        // Fetch Looks and Clothes
        return new SearchResponse(lookService.getLooksByInclination(inclination));
    }

    @PostMapping(value="upload")
    @ResponseBody
    public Boolean upload(@RequestParam Request request) {
        // Uploading
        return true;
    }

}
