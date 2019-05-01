package com.example.homie.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.homie.DRO.SensorData;
import com.example.homie.R;
import com.example.homie.viewModel.SensorsViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TestSensorsActivity extends AppCompatActivity {

    private String deviceTitle;

    private TextView movementSensorTitle;
    private LinearLayout movementCharts;
    private LineChart movementLineChart;
    private List<SensorData> movementData;

    private SensorsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_activity_sensors_list);

        deviceTitle = getIntent().getExtras().getString("deviceTitle");
        initToolbar();
        initViewModel();

        initMovementCharts();
        initMovementData();
        //setupMovementSensorData();

        int white = getResources().getColor(R.color.colorWhite);
        movementCharts.setBackgroundColor(white);

    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(deviceTitle);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(SensorsViewModel.class);
        viewModel.getMovementData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(@Nullable List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    movementData = sensorsData;
                    setupMovementSensorData();
                }
            }
        });
    }

    private void initMovementCharts() {
        movementLineChart = findViewById(R.id.movement_sensor_line_chart);
        movementCharts = findViewById(R.id.movement_sensor_charts);
        movementSensorTitle = findViewById(R.id.movement_sensor);
        movementSensorTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movementCharts.getVisibility() == View.GONE) {
                    movementCharts.setVisibility(View.VISIBLE);
                } else {
                    movementCharts.setVisibility(View.GONE);
                }
            }
        });
        //TODO to be continued
    }

    private void initMovementData() {
        viewModel.loadMovementData();
    }

    private void setupMovementSensorData() {
        // remember first item's timestamp - referenceTimestamp
        long referenceTimestamp = (new Timestamp(movementData.get(0).getDate().getTime())).getTime();

        List<Entry> entries = new ArrayList<>(movementData.size() + 1);
        Entry e;
        for (int i = 0; i < movementData.size(); i++) {
            SensorData sensorEntry = movementData.get(i);
            //convert SensorData timestamps to short timestamps
            float Xnew = (new Timestamp(sensorEntry.getDate().getTime())).getTime() - referenceTimestamp;
            e = new Entry(Xnew, sensorEntry.getValue());
            entries.add(e);
        }

        //setup chart
        LineDataSet lineDataSet = new LineDataSet(entries, "Daily average");
        // disable circles
        // lineDataSet.setDrawCircles(false);
        lineDataSet.setCircleRadius(2);
        LineData lineData = new LineData(lineDataSet);

        ValueFormatter xAxisFormatter = new DateAxisValueFormatter(referenceTimestamp);
        XAxis xAxis = movementLineChart.getXAxis();
        xAxis.setValueFormatter(xAxisFormatter);

        movementLineChart.setData(lineData);
        movementLineChart.invalidate();
    }

}
