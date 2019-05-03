package com.example.homie.viewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.example.homie.DRO.MovementDRO;
import com.example.homie.DRO.SensorData;
import com.example.homie.repositories.SensorRepository;
import com.example.homie.viewModels.util.StatusCode;

import java.util.List;

public class SensorsViewModel extends AndroidViewModel implements MovementSensorCallBack{

    private SensorRepository sensorRepository;
    private MutableLiveData<List<SensorData>> movementData;

    public SensorsViewModel(@NonNull Application application) {
        super(application);
        sensorRepository = SensorRepository.getInstance();
        movementData = new MutableLiveData<>();
    }

    public LiveData<List<SensorData>> getMovementData() {
        return movementData;
    }

    public void loadMovementData(){
        sensorRepository.getMovementData(this);

    }

    @Override
    public void onReturn(MovementDRO response) {
        if(response.getStatusCode() == StatusCode.OK){
            movementData.setValue(response.getSensorDataList());
        }

    }


}
