package com.example.SmartCloset.model;


import com.mongodb.lang.Nullable;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class LikeRequest {
    @NonNull
    private String id;
    @Nullable
    private String lookId;
    @Nullable
    private String clothId;
    @Nullable
    private String userId; // 사람 좋아요 기능에서의 user id

    public LikeRequestType getRequestType() {
        if (lookId != null) {
            return LikeRequestType.LOOK;
        } else if (clothId != null) {
            return LikeRequestType.CLOTH;
        } else if (userId != null) {
            return LikeRequestType.USER;
        }
        return null;
    }
}
