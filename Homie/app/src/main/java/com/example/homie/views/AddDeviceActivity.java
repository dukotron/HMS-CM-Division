package com.example.homie.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.homie.R;
import com.example.homie.viewModels.AddDeviceViewModel;

public class AddDeviceActivity extends AppCompatActivity {

    private AddDeviceViewModel viewModel;
    private EditText deviceLocation;
    private EditText deviceId;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        initToolbar();
        initViewModel();
        deviceLocation = findViewById(R.id.add_device_location);
        deviceId = findViewById(R.id.add_device_id);
        progressBar = findViewById(R.id.progressBar_add_device);
        initAddBtn();
    }

    private void initToolbar() {

    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(AddDeviceViewModel.class);

        viewModel.getIsAdded().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isAdded) {
                if (isAdded) {
                    Toast.makeText(AddDeviceActivity.this, "Device was added", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        viewModel.getIsValidId().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isValidId) {
                if (!isValidId) {
                    progressBar.setVisibility(View.GONE);
                    deviceId.setError("Empty ID");
                }
            }
        });

        viewModel.getIsValidLocation().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isValidLocation) {
                if (!isValidLocation) {
                    progressBar.setVisibility(View.GONE);
                    deviceLocation.setError("Empty Location");
                }
            }
        });

        viewModel.getShowError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean showError) {
                if(showError){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(AddDeviceActivity.this, "Device wasn't added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initAddBtn() {
        Button btn = findViewById(R.id.add_device_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.addDevice(deviceLocation.getText().toString(), deviceId.getText().toString());
            }
        });
    }

}
