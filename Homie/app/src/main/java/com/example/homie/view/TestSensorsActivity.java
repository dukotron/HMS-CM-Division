package com.example.homie.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;

import com.example.homie.R;
import com.github.mikephil.charting.charts.LineChart;

import java.util.Date;
import java.util.HashMap;

public class TestSensorsActivity extends AppCompatActivity {

    private LineChart movementLineChart;

    private HashMap<Date,Integer> movementData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.test_activity_sensors_list);

        getMovementData();

    }

    public void getMovementData(){
        //TODO get sensors data and load it to the graph
    }
}
