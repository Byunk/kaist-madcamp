package com.example.SmartCloset.model.api;

import com.example.SmartCloset.model.ClosetEnum.*;
import com.mongodb.lang.Nullable;
import lombok.Data;

@Data
public class SearchRequest {

    private String userId;
    private Boolean isSearchTab;
    private Integer numOutput;

    @Nullable
    private Category category;
    @Nullable
    private ClothesColor color;
    @Nullable
    private TPO tpo;

}
