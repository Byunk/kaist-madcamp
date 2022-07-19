package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.*;
import com.example.SmartCloset.model.api.EditRequest;
import com.example.SmartCloset.model.api.FileDto;
import com.example.SmartCloset.model.api.SearchRequest;
import com.example.SmartCloset.model.api.UploadRequest;
import com.example.SmartCloset.model.api.exception.ErrorCode;
import com.example.SmartCloset.model.api.exception.InvalidInputException;
import com.example.SmartCloset.model.api.exception.UserNotFoundException;
import com.example.SmartCloset.service.LookService;
import com.example.SmartCloset.service.UserService;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

//for image upload
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.*;
import java.util.UUID;

import javax.servlet.UnavailableException;

@RestController
@RequestMapping("look")
@CrossOrigin(origins = "http://localhost:3000")
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
    public Look findLookById(@RequestParam String id) {
        return lookService.getLookById(id);
    }

    @PutMapping("id")
    public void deleteLookById(@RequestParam String id) {
        lookService.delete(id);
    }

    @PutMapping("edit/id")
    public Look editLookById(@RequestBody EditRequest request) {
        User user = userService.getUserById(request.getId());
        if (user == null) {
            throw new UserNotFoundException("User Not Found", ErrorCode.USER_NOT_FOUND);
        }
        
        Look look = lookService.getLookById(request.getLookId());
        if (look == null) {
            throw new InvalidInputException("Invalid Input Exception", ErrorCode.INVALID_INPUT);
        }
        if (request.getImgUrl() != null) {
            look.setImgUrl(request.getImgUrl());
        }
        look.setGender(request.getGender());
        look.setWeather(request.getWeather());
        look.setTpo(request.getTpos());
        look.setClothes(request.getClothes());
        return lookService.saveOrUpdate(look);
    }

    // Search
    @PostMapping("search")
    public ArrayList<Look> search(@RequestBody SearchRequest request) {
        return lookService.search(request);
    }

    // Upload
    @PostMapping("upload")
    public void upload(@RequestBody UploadRequest request) {
        User user = userService.getUserById(request.getId());
        if (user == null) {
            throw new UserNotFoundException("User Not Found", ErrorCode.USER_NOT_FOUND);
        }
        Look newlook = new Look(request.getGender(), request.getWeather(), request.getTpos(), request.getClothes());
        newlook.setImgUrl(request.getImgUrl());

        // Upload Look Collection
        Look look = lookService.saveOrUpdate(newlook);

        if (user.getUploadLook() != null) {
            user.getUploadLook().add(look.getLookId());
        } else {
            ArrayList<String> uploadLook = new ArrayList<>();
            user.setUploadLook(uploadLook);
        }
        
        userService.saveOrUpdate(user);
    }

    @PostMapping("uploadImg")
    // 업로드하는 파일들을 MultipartFile 형태의 파라미터로 전달된다.
    public String uploadImg(@RequestPart(value="file", required = false) MultipartFile file)
                                                throws IllegalStateException, IOException {
        String curWorkingDir = System.getProperty("user.dir");
        String path = curWorkingDir + "/src/main/resources/static/save_image";
        String returnpath = "";
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
            File newFileName = new File(path, dto.getUuid() + "_" + dto.getFileName());
            // 전달된 내용을 실제 물리적인 파일로 저장해준다.
            file.transferTo(newFileName);
            returnpath += dto.getUuid() + "_" + dto.getFileName();
        }
        //model.addAttribute("files", list);
        return returnpath;
    }

	@GetMapping(value = "image/{imagename}", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<byte[]> imageSearch(@PathVariable("imagename") String imagename) throws IOException {
        String curWorkingDir = System.getProperty("user.dir");
        String path = curWorkingDir + "/src/main/resources/static/save_image/";
		InputStream imageStream = new FileInputStream(path + imagename);
		byte[] imageByteArray = IOUtils.toByteArray(imageStream);
		imageStream.close();
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}

}

