package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.*;
import com.example.SmartCloset.model.api.FileDto;
import com.example.SmartCloset.model.api.SearchRequest;
import com.example.SmartCloset.model.api.UploadRequest;
import com.example.SmartCloset.model.api.SearchRequest.SearchClothRequest;
import com.example.SmartCloset.service.ClothService;
import com.example.SmartCloset.service.LookService;
import com.example.SmartCloset.service.UserService;
import com.example.SmartCloset.model.ClosetEnum.*;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//for image upload
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.lang.reflect.Array;
import java.io.*;
import java.util.UUID;
import java.util.stream.Stream;

@RestController
@RequestMapping("look")
public class LookController {

    private final LookService lookService;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public LookController(LookService lookService, UserService userService) {
        this.lookService = lookService;
        this.userService = userService;
    }

    @GetMapping("id")
    public Look id(@RequestParam String id) {
        return lookService.getLookById(id);
    }

    // Search
    @PostMapping("search")
    public ArrayList<Look> search(@RequestBody SearchRequest request) {
        return lookService.search(request);
    }

    // Upload
    @PostMapping("upload")
    public void upload(@RequestParam UploadRequest request) {
        User user = userService.getUserById(request.getId());
        Look newlook = new Look(request.getGender(), request.getTpos(), request.getClothes());

        // Upload Look Collection
        Look look = lookService.saveOrUpdate(newlook);

        user.getUploadLook().add(look.getLookId());
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

