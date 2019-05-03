package com.example.homie.network.retrofit;

import com.example.homie.DRO.AuthDRO;
import com.example.homie.DTO.UserLoginDTO;
import com.example.homie.viewModels.AuthCallBack;


import retrofit2.Callback;

public interface LoginCallback extends Callback<AuthDRO> {

    void start(UserLoginDTO user, AuthCallBack callBack);
}
