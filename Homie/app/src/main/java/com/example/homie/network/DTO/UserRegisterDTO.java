package com.example.homie.network.DTO;

public class UserRegisterDTO {

    public String firstName;
    public String lastName;
    public String email;
    public byte[] password;
    public byte[] salt;

    public UserRegisterDTO(String firstName, String lastName, String email, byte[] password, byte[] salt) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.salt = salt;
    }
}
