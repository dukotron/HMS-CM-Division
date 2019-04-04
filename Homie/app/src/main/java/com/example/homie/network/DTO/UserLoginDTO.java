package com.example.homie.network.DTO;

public class UserLoginDTO {

    public String email;
    public byte[] password;
    public byte[] salt;

    public UserLoginDTO(String email, byte[] password,byte[] salt) {
        this.email = email;
        this.password = password;
        this.salt=salt;
    }
}
