package com.example.SmartCloset.model.ClosetEnum;

public enum TPO {

    DATE("데이트"),
    CAMPUS("캠퍼스"),
    FORMAL("포멀"),
    CASUAL("캐주얼"),
    LOVELY("러블리"),
    DAILY("데일리");

    private String tpo;
    TPO(String tpo) { this.tpo = tpo; }
    public String getTpo() { return tpo; }

}
