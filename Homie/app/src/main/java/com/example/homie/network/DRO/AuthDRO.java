package com.example.homie.network.DRO;

public class AuthDRO {

    public String firstName;
    public String lastName;
    public int statusCode;
    public String userId;

    public AuthDRO(String firstName, String lastName, int statusCode, String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.statusCode = statusCode;
        this.userId = userId;
    }
}
