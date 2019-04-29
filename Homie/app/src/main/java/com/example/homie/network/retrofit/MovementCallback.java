package com.example.homie.network.retrofit;

import com.example.homie.DRO.MovementDRO;

import java.util.List;

import retrofit2.Callback;

public interface MovementCallback extends Callback<List<MovementDRO>> {

    void start(int userId);
}
