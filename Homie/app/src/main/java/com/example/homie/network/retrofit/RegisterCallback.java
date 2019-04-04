package com.example.homie.network.retrofit;

import com.example.homie.network.DRO.AuthDRO;
import com.example.homie.network.DTO.UserRegisterDTO;

import retrofit2.Callback;

public interface RegisterCallback extends Callback<UserRegisterDTO> {

    AuthDRO start(UserRegisterDTO user);
}
