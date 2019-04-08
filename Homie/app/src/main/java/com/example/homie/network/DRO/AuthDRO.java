package com.example.homie.network.DRO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AuthDRO implements Serializable {

    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("statusCode")
    @Expose
    private int statusCode;
    @SerializedName("userId")
    @Expose
    private String userId;

    public AuthDRO(String firstName, String lastName, int statusCode, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.statusCode = statusCode;
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getUserId() {
        return userId;
    }
}
