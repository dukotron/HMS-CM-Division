package com.example.homie.network;

import com.example.homie.network.DTO.UserLoginDTO;
import com.example.homie.network.DTO.UserRegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIConnection {

    @FormUrlEncoded
    @POST("api/user/register")
    Call<UserRegisterDTO> createAccount(@Field("firstName") String firstName,
                                        @Field("lastName") String lastName,
                                        @Field("email") String email,
                                        @Field("password") String password);

    //change to POST

    @GET("api/user/login")
    Call<UserLoginDTO> loginAccount(@Field("email") String email,
                                    @Field("password") String password) ;


}

