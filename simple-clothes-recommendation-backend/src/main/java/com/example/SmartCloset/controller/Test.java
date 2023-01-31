package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.ClosetEnum.Category;
import com.example.SmartCloset.model.ClosetEnum.ClothesColor;
import com.example.SmartCloset.model.ClosetEnum.Gender;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.example.SmartCloset.model.ClosetEnum.Weather;
import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.model.api.UploadRequest;
import com.example.SmartCloset.model.User;
import com.example.SmartCloset.service.LookService;
import com.example.SmartCloset.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("test")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Test {

    private final LookController lookController;
    private final UserService userService;
    private final LookService lookService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public Test(LookController lookController, UserService userService, LookService lookService) {
        this.lookController = lookController;
        this.userService = userService;
        this.lookService = lookService;
    }

    @GetMapping("create/user")
    public void user(@RequestParam Integer num) {
        for (int i = 0; i<num; i++) {
            User user = new User(
                    "test" + String.valueOf(i),
                    "test" + String.valueOf(i),
                    "tester" + String.valueOf(i)
            );
            userService.saveOrUpdate(user);
        }
        logger.info(userService.countUser().toString());
    }

    @GetMapping("create/look")
    public void look(@RequestParam String id, Integer num) {
        for (int i = 0; i<num; i++) {
            ArrayList<Cloth> clothes = new ArrayList<>();
            Category[] categories = {Category.TOP, Category.BOTTOM, Category.SHOES};

            // Generate Clothes
            for (Category category : categories) {
                Cloth cloth = new Cloth(category, category.getRandomSubCategory(), ClothesColor.getRandomColor());
                clothes.add(cloth);
            }

            UploadRequest request = new UploadRequest(id, Gender.MAN, 
            Weather.getRandomWeather(), new ArrayList<>(Arrays.asList(TPO.getRandomTPO())), clothes);

            lookController.upload(request);
        }
    }

    @GetMapping("findByName")
    public User find(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @GetMapping("create/test")
    public String test() {
        return "Test Success!";
    }

    @GetMapping("deleteAll")
    public void deleteAll(@RequestParam String id) {
        userService.deleteAll(id);
    }

}

