package com.example.homie.DRO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthDRO implements Serializable {

    @Expose
    private int statusCode;
    @SerializedName("UserId")
    @Expose
    private String userId;

    public AuthDRO(int statusCode) {
        this.statusCode = statusCode;
    }

    public AuthDRO(int statusCode, String userId) {
        this.statusCode = statusCode;
        this.userId = userId;
    }


    public int getStatusCode() {
        return statusCode;
    }

    public String getUserId() {
        return userId;
    }
}
