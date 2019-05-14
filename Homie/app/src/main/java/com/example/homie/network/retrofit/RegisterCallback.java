package com.example.homie.network.retrofit;

import com.example.homie.DTO.UserRegisterDTO;
import com.example.homie.viewModels.AuthCallBack;

import retrofit2.Callback;

public interface RegisterCallback extends Callback<Integer> {

    void start(UserRegisterDTO user, AuthCallBack callBack);
}
