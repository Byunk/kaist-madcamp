package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.*;
import com.example.SmartCloset.model.api.FileDto;
import com.example.SmartCloset.model.api.SearchRequest;
import com.example.SmartCloset.model.api.SearchResponse;
import com.example.SmartCloset.model.api.UploadRequest;
import com.example.SmartCloset.service.ClothService;
import com.example.SmartCloset.service.LookService;
import com.example.SmartCloset.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//for image upload
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File; 
import java.io.*;
import java.util.UUID;

@RestController
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
    public Look id(@RequestParam String id) {
        return lookService.getLookById(id);
    }

    @PostMapping("search")
    public SearchResponse search(@RequestBody SearchRequest request) {
        logger.info(request.getUserId());
        logger.info(request.getIsSearchTab().toString());

        // Get User
        User user = userService.getUserById(request.getUserId());
        if (user == null) {
            return null;
        }

        // Get Liked Clothes List
        ArrayList<String> likedLooksIds = user.getLikedLook();
        if (likedLooksIds == null) {
            return null;
        }

        ArrayList<Look> likedLooks = lookService.getLooksById(likedLooksIds);

        // Calculate Inclination
        Inclination inclination = new Inclination();
        inclination.setTpoDistribution(lookService.getTPODistribution(likedLooks));

        // Fetch Looks and Clothes
        return new SearchResponse(lookService.getLooksByInclination(inclination, request.getNumOutput()));
    }

    @PostMapping("upload")
    public void upload(@RequestParam UploadRequest request) {
        ArrayList<String> clothesIds = new ArrayList<>();

        // Upload Clothes DB
        for (Cloth cloth: request.getClothes()) {
            clothService.saveOrUpdate(cloth);
            clothesIds.add(cloth.getClothId());
        }

        // Upload Look DB
        Look look = new Look(request.getTpos(), request.getGender(), clothesIds);
        lookService.saveOrUpdate(look);

        // Upload User DB
        User user = userService.getUserById(request.getId());
        if (user == null) {
            return;
        }

        if (user.getLook() == null) {
            ArrayList<String> userLooks = new ArrayList<>();
            userLooks.add(look.getLookId());
            user.setLook(userLooks);
        } else {
            ArrayList<String> userLooks = user.getLook();
            userLooks.add(look.getLookId());
            user.setLook(userLooks);
        }
        userService.saveOrUpdate(user);
    }

    @PostMapping("uploadImg")
    // 업로드하는 파일들을 MultipartFile 형태의 파라미터로 전달된다.
    public String uploadImg(@RequestPart(value="file", required = false) MultipartFile file)
                                                throws IllegalStateException, IOException {
        ArrayList<FileDto> list = new ArrayList<>();
        String curWorkingDir = System.getProperty("user.dir");
        String path = curWorkingDir + "/save_image";
        File dir = new File(path);

        if(!dir.exists()) {
            dir.mkdir();
            System.out.println("생성 완료");
        } else {
            System.out.println("같은 이름의 폴더가 이미 존재합니다.");
        }   

            if (!file.isEmpty()) {
                // UUID를 이용해 unique한 파일 이름을 만들어준다.
                FileDto dto = new FileDto(UUID.randomUUID().toString(), 
                                      file.getOriginalFilename(), 
                                      file.getContentType());
                list.add(dto);

                File newFileName = new File(path, dto.getUuid() + "_" + dto.getFileName());
                // 전달된 내용을 실제 물리적인 파일로 저장해준다.
                file.transferTo(newFileName);
            }
        //model.addAttribute("files", list);
        return "result";
    }

}
