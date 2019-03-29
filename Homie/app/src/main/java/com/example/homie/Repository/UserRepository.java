package com.example.homie.Repository;

import com.example.homie.Network.APIConnection;
import com.example.homie.Network.DWHConnection;

public class UserRepository {

    private APIConnection apiConnection;
    private static UserRepository instance;

    private UserRepository(){
        apiConnection = new DWHConnection();
    }
    public static UserRepository getInstance() {
        if(instance==null){
            instance = new UserRepository();
        }
        return instance;
    }

    public void createNewAccount(String firstName,String lastName,String email,String password){
        apiConnection.createNewAccount(firstName,lastName,email,password);
    }
}
