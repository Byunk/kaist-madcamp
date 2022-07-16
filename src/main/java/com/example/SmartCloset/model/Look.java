package com.example.SmartCloset.model;

import com.example.SmartCloset.model.ClosetEnum.Gender;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("look")
public class Look {

    @Id
    private String lookId;

    private String tpo;
    private Gender gender;
    private String url;

    private ArrayList<String> clothes;

    public String getlookID(){
        return lookId;
    }

    public String gettpo(){
        return tpo;
    }

    public Gender getgender(){
        return gender;
    }

    public String url(){
        return url;
    }

    public ArrayList<String> getclothes(){
        return clothes;
    }

    public void setlookID(String lookId){
        this.lookId = lookId;
    }

    public void settpo(String tpo){
        this.tpo = tpo;
    }

    public void setgender(Gender gender){
        this.gender = gender;
    }

    public void seturl(String url){
        this.url = url;
    }


    public void setclothes(ArrayList<String> clothes){
        this.clothes = clothes;
    }

}

