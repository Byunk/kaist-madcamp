package com.example.SmartCloset.model.api;

import java.util.ArrayList;

import com.example.SmartCloset.model.ClosetEnum.*;
import com.mongodb.lang.Nullable;
import lombok.Data;

@Data
public class SearchRequest {

    private String id;

    private Gender gender;
    private Integer numOutput;

    @Nullable
    private ArrayList<TPO> tpos;

    private ArrayList<SearchClothRequest> clothRequests;
    
    @Data
    public class SearchClothRequest {
        private Category category;
        @Nullable
        private String subCategory;
        @Nullable
        private ClothesColor color;
    }
}


