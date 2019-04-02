package com.example.homie.network.DTO;

public class UserLoginDTO {

    public String email;
    public String password;

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
