package com.example.SmartCloset.model;

public class LikeRequest {
    private String id;
    private String lookId;
    private String clothId;
    private String userId; // 사람 좋아요 기능에서의 user id

    LikeRequest(String id, String lookId, String clothId, String userId) {
        assert(id != null);
        assert(lookId != null && clothId == null & userId == null || lookId == null && clothId != null & userId == null
                || lookId == null && clothId == null & userId != null);
        this.id = id;
        this.lookId = lookId;
        this.clothId = clothId;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public String getClothId() {
        return clothId;
    }

    public String getLookId() {
        return lookId;
    }

    public String getUserId() {
        return userId;
    }
}
