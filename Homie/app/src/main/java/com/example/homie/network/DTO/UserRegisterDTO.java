package com.example.homie.network.DTO;

public class UserRegisterDTO {

    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public byte[] salt;

    public UserRegisterDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
