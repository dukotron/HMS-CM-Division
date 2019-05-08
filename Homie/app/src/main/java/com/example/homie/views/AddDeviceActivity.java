package com.example.homie.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.homie.R;
import com.example.homie.viewModels.AddDeviceViewModel;

public class AddDeviceActivity extends AppCompatActivity {

    private AddDeviceViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        viewModel = ViewModelProviders.of(this).get(AddDeviceViewModel.class);
    }
}
