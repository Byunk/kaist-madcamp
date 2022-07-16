package com.example.SmartCloset.model;

import com.example.SmartCloset.model.ClosetEnum.*;
import com.mongodb.lang.Nullable;

public class Request {

    private String userId;
    private Boolean isSearchTab;

    @Nullable
    private Category1 category1;
    @Nullable
    private Category2 category2;
    @Nullable
    private ClothesColor color;
    @Nullable
    private TPO tpo;

    public String getUserId() {
        return this.userId;
    }
}
