package com.example.homie.view;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.homie.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TestSensorsActivity extends AppCompatActivity {

    private String deviceTitle;

    private TextView movementSensorTitle;
    private LinearLayout movementCharts;
    private LineChart movementLineChart;
    private HashMap<Date,Integer> movementData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_sensors_list);

        deviceTitle = getIntent().getExtras().getString("deviceTitle");
        initToolbar();

        initMovementCharts();
        getMovementData();
        setupMovementSensorData();
    }

    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(deviceTitle);
    }

    private void initMovementCharts(){
        movementLineChart = findViewById(R.id.movement_sensor_line_chart);
        movementCharts = findViewById(R.id.movement_sensor_charts);
        movementSensorTitle = findViewById(R.id.movement_sensor);
        movementSensorTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(movementCharts.getVisibility() == View.GONE){
                    movementCharts.setVisibility(View.VISIBLE);
                }else{
                    movementCharts.setVisibility(View.GONE);
                }
            }
        });
        //TODO to be continued
    }

    private void setupMovementSensorData(){
        List<Entry> entries = new ArrayList<>();
        Entry e;
        for (int i = 0; i < 10; i++) {
            e = new Entry(i,i*10);
            entries.add(e);
        }
        LineDataSet lineDataSet = new LineDataSet(entries,"Cancer percentage");
        // disable circles
        // lineDataSet.setDrawCircles(false);
        lineDataSet.setCircleRadius(2);
        LineData lineData = new LineData(lineDataSet);
        movementLineChart.setData(lineData);
        movementLineChart.invalidate();
    }

    public void getMovementData(){
        //TODO get sensors data and load it to the graph

    }
}
