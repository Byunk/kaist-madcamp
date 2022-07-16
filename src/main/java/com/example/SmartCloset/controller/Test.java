package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.ClosetEnum.Category;
import com.example.SmartCloset.model.ClosetEnum.Gender;
import com.example.SmartCloset.model.ClosetEnum.TPO;
import com.example.SmartCloset.model.Cloth;
import com.example.SmartCloset.model.Look;
import com.example.SmartCloset.model.User;
import com.example.SmartCloset.service.ClothService;
import com.example.SmartCloset.service.LookService;
import com.example.SmartCloset.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("test")
public class Test {

    private final UserService userService;
    private final LookService lookService;
    private final ClothService clothService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public Test(UserService userService, LookService lookService, ClothService clothService) {
        this.userService = userService;
        this.lookService = lookService;
        this.clothService = clothService;
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
    public void look(@RequestParam Integer num) {
        for (int i = 0; i<num; i++) {
            ArrayList<Cloth> clothes = new ArrayList();
            for (int j = 0; j<3; j++) {
                Cloth cloth = new Cloth(Category.TOP, Gender.MAN);
                cloth.setSubCategory(Category.TOP.getRandomSubCategory());
                cloth.set
                clothes.add(new Cloth(Category.TOP, Gender.MAN));

            }

            Look look = new Look(
                    null,
                    TPO.CASUAL,
                    Gender.MAN,

            );
            lookService.saveOrUpdate(look);
        }
        logger.info(userService.countUser().toString());
    }

    @GetMapping("findByName")
    public User find(@RequestParam String id) {
        return userService.getUserById(id);
    }

    @GetMapping("create/test")
    public String test() {
        return "Test Success!";
    }

}
