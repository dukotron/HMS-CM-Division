package com.example.homie.network.retrofit;

import com.example.homie.network.DRO.AuthDRO;
import com.example.homie.network.DTO.UserLoginDTO;
import com.example.homie.viewModel.AuthCallBack;


import retrofit2.Callback;

public interface LoginCallback extends Callback<AuthDRO> {

    void start(UserLoginDTO user, AuthCallBack callBack);
}
