package com.example.SmartCloset.model.api;

import com.example.SmartCloset.model.Look;

import java.util.ArrayList;

public class SearchResponse {

    private ArrayList<Look> looks;
    public SearchResponse(ArrayList<Look> looks) {
        this.looks = looks;
    }

}
