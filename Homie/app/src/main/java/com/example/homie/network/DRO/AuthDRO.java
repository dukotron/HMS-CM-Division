package com.example.homie.network.DRO;

public class AuthDRO {

    private String firstName;
    private String lastName;
    private int statusCode;
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
