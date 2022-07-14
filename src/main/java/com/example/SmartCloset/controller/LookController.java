package com.example.SmartCloset.controller;

import com.example.SmartCloset.model.Look;
import com.example.SmartCloset.model.Request;
import com.example.SmartCloset.service.LookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("look")
public class LookController {

    private final LookService lookService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public LookController(LookService lookService) { this.lookService = lookService; }

    @GetMapping("id")
    @ResponseBody
    public Look id(@RequestParam String id) {
        return lookService.getLookById(id);
    }
/*
    @PostMapping(value="search")
    @ResponseBody
    public <Look, Cloth, User> search(@RequestParam Request request) {
        // Searching
    }*/

    @PostMapping(value="upload")
    @ResponseBody
    public Boolean upload(@RequestParam Request request) {
        // Uploading
        return true;
    }

}
