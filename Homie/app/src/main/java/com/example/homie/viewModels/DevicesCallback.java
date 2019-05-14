package com.example.homie.viewModels;

import com.example.homie.DRO.DevicesListDRO;

public interface DevicesCallback {
    void onDelete(int statusCode);
    void onReturnAllDevices(DevicesListDRO devices);
}
