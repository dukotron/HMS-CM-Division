package com.example.homie.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserRegisterDTO implements Serializable {
    @SerializedName("UserId")
    @Expose
    private String userId;

    public UserRegisterDTO(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "UserId='" + userId +
                '}';
    }
}
