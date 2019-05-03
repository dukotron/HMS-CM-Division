package com.example.homie.viewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.example.homie.DRO.MovementDRO;
import com.example.homie.DRO.SensorData;
import com.example.homie.repository.SensorRepository;

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
        //sensorRepository.getMovementData(Integer.valueOf(TempMemory.getUserId(getApplication().getApplicationContext())),this);
        //The ID which we are getting is 2fc47dc1-e4e5-469c-a2c0-3517530f9942 and they are asking for an ID of different type, should be string not int
        sensorRepository.getMovementData(1234,this);

    }

    @Override
    public void onReturn(MovementDRO response) {
        movementData.setValue(response.getSensorDataList());
    }


}
