package com.example.homie.views;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.homie.DRO.SensorData;
import com.example.homie.R;
import com.example.homie.viewModels.SensorsViewModel;
import com.example.homie.views.formatters.DateAxisValueFormatter;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SensorsActivity extends AppCompatActivity {

    private String deviceTitle;
    private DateFormat dateFormat;

    private TextView movementSensorTitle;
    private EditText movementDateFrom;
    private EditText movementDateTo;
    private Button movementBtn;
    private LinearLayout movementCharts;
    private LineChart movementLineChart;
    private List<SensorData> movementData;

    private TextView coSensorTitle;
    private LinearLayout coCharts;
    private BarChart coBarChart;
    private List<SensorData> coData;

    private TextView temperatureSensorTitle;
    private LinearLayout temperatureCharts;
    private BarChart temperatureBarChart;
    private List<SensorData> temperatureData;

    private TextView humiditySensorTitle;
    private LinearLayout humidityCharts;
    private LineChart humidityLineChart;
    private List<SensorData> humidityData;

    private SensorsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_list);

        deviceTitle = getIntent().getExtras().getString("deviceTitle");
        initToolbar();
        initViewModel();

        dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        initMovementCharts();
        initMovementData();

        initCoCharts();
        //initCoData();

        initTemperatureCharts();
        //initTemperatureData();

        initHumidityCharts();
        //initHumidityData();
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

        viewModel.getCoData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(@Nullable List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    coData = sensorsData;
                    setupCoSensorData();
                }
            }
        });

        viewModel.getTemperatureData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(@Nullable List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    temperatureData = sensorsData;
                    setupTemperatureSensorData();
                }
            }
        });

        viewModel.getHumidityData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(@Nullable List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    humidityData = sensorsData;
                    setupHumiditySensorData();
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
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH) + 1;
        int year = cldr.get(Calendar.YEAR);
        movementDateTo = findViewById(R.id.movement_date_to);
        movementDateTo.setText(day + "-" + month + "-" + year);
        cldr.add(Calendar.DATE, -7);
        day = cldr.get(Calendar.DAY_OF_MONTH);
        month = cldr.get(Calendar.MONTH) + 1;
        year = cldr.get(Calendar.YEAR);
        movementDateFrom = findViewById(R.id.movement_date_from);
        movementDateFrom.setText(day + "-" + month + "-" + year);
        movementBtn = findViewById(R.id.movement_btn_show);
        movementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initMovementData();
            }
        });
    }

    private void initMovementData() {

        try {
            viewModel.loadMovementData(dateFormat.parse(movementDateFrom.getText().toString()),
                    dateFormat.parse(movementDateTo.getText().toString()));
        } catch (ParseException e) {
            Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupMovementSensorData() {
        Toast.makeText(this, "YEEEEEe", Toast.LENGTH_SHORT).show();
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

        // setup chart
        LineDataSet lineDataSet = new LineDataSet(entries, "Daily average");
        lineDataSet.setCircleRadius(2);
        LineData lineData = new LineData(lineDataSet);
        // set value formatter for x axis to show dates
        ValueFormatter xAxisFormatter = new DateAxisValueFormatter(referenceTimestamp);
        XAxis xAxis = movementLineChart.getXAxis();
        xAxis.setValueFormatter(xAxisFormatter);
        // stile the chart
        movementLineChart.getAxisLeft().setTextColor(Color.WHITE);
        movementLineChart.getXAxis().setTextColor(Color.WHITE);
        movementLineChart.getAxisRight().setEnabled(false);
        int bgColor = getResources().getColor(R.color.transparent);
        movementCharts.setBackgroundColor(bgColor);
        // set data and notify the chart
        movementLineChart.setData(lineData);
        movementLineChart.invalidate();
    }

    private void initCoCharts() {
        coBarChart = findViewById(R.id.co_sensor_bar_chart);
        coCharts = findViewById(R.id.co_sensor_charts);
        coSensorTitle = findViewById(R.id.co_sensor);
        coSensorTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (coCharts.getVisibility() == View.GONE) {
                    coCharts.setVisibility(View.VISIBLE);
                } else {
                    coCharts.setVisibility(View.GONE);
                }
            }
        });
    }

    /*
        private void initCoData(){
            viewModel.loadCoData();
        }
    */
    private void setupCoSensorData() {
        // remember first item's timestamp - referenceTimestamp
        long referenceTimestamp = (new Timestamp(coData.get(0).getDate().getTime())).getTime();

        List<BarEntry> entries = new ArrayList<>(coData.size() + 1);
        BarEntry e;
        for (int i = 0; i < coData.size(); i++) {
            SensorData sensorEntry = coData.get(i);
            //convert SensorData timestamps to short timestamps
            float Xnew = (new Timestamp(sensorEntry.getDate().getTime())).getTime() - referenceTimestamp;
            e = new BarEntry(Xnew, sensorEntry.getValue());
            entries.add(e);
        }

        // setup chart
        BarDataSet barDataSet = new BarDataSet(entries, "Daily average");
        BarData barData = new BarData(barDataSet);
        // set value formatter for x axis to show dates
        ValueFormatter xAxisFormatter = new DateAxisValueFormatter(referenceTimestamp);
        XAxis xAxis = coBarChart.getXAxis();
        xAxis.setValueFormatter(xAxisFormatter);
        // stile the chart
        barData.setBarWidth(0.9f);

        coBarChart.getAxisLeft().setTextColor(Color.WHITE);
        coBarChart.getXAxis().setTextColor(Color.WHITE);
        coBarChart.getAxisRight().setEnabled(false);
        int bgColor = getResources().getColor(R.color.transparent);
        coCharts.setBackgroundColor(bgColor);
        // set data and notify the chart
        coBarChart.setData(barData);
        coBarChart.setFitBars(true);
        coBarChart.invalidate();
    }

    private void initTemperatureCharts() {
        temperatureBarChart = findViewById(R.id.temperature_sensor_bar_chart);
        temperatureCharts = findViewById(R.id.temperature_sensor_charts);
        temperatureSensorTitle = findViewById(R.id.temperature_sensor);
        temperatureSensorTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temperatureCharts.getVisibility() == View.GONE) {
                    temperatureCharts.setVisibility(View.VISIBLE);
                } else {
                    temperatureCharts.setVisibility(View.GONE);
                }
            }
        });
    }

    /*
        private void initTemperatureData(){
            viewModel.loadTemperatureData();
        }
    */
    private void setupTemperatureSensorData() {
        // remember first item's timestamp - referenceTimestamp
        long referenceTimestamp = (new Timestamp(temperatureData.get(0).getDate().getTime())).getTime();

        List<BarEntry> entries = new ArrayList<>(temperatureData.size() + 1);
        BarEntry e;
        for (int i = 0; i < temperatureData.size(); i++) {
            SensorData sensorEntry = temperatureData.get(i);
            //convert SensorData timestamps to short timestamps
            float Xnew = (new Timestamp(sensorEntry.getDate().getTime())).getTime() - referenceTimestamp;
            e = new BarEntry(Xnew, sensorEntry.getValue());
            entries.add(e);
        }

        // setup chart
        BarDataSet barDataSet = new BarDataSet(entries, "Daily average");
        barDataSet.setColor(Color.RED);
        BarData barData = new BarData(barDataSet);
        // set value formatter for x axis to show dates
        ValueFormatter xAxisFormatter = new DateAxisValueFormatter(referenceTimestamp);
        XAxis xAxis = temperatureBarChart.getXAxis();
        xAxis.setValueFormatter(xAxisFormatter);
        // stile the chart
        barData.setBarWidth(0.5f);

        temperatureBarChart.getAxisLeft().setTextColor(Color.WHITE);
        temperatureBarChart.getXAxis().setTextColor(Color.WHITE);
        temperatureBarChart.getAxisRight().setEnabled(false);
        int bgColor = getResources().getColor(R.color.transparent);
        temperatureBarChart.setBackgroundColor(bgColor);
        // set data and notify the chart
        temperatureBarChart.setData(barData);
        temperatureBarChart.setFitBars(true);
        temperatureBarChart.setDrawBarShadow(false);
        temperatureBarChart.invalidate();
    }

    private void initHumidityCharts() {
        humidityLineChart = findViewById(R.id.humidity_sensor_line_chart);
        humidityCharts = findViewById(R.id.humidity_sensor_charts);
        humiditySensorTitle = findViewById(R.id.humidity_sensor);
        humiditySensorTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (humidityCharts.getVisibility() == View.GONE) {
                    humidityCharts.setVisibility(View.VISIBLE);
                } else {
                    humidityCharts.setVisibility(View.GONE);
                }
            }
        });
    }

    /*
        private void initHumidityData(){
            viewModel.loadHumidityData();
        }
    */
    private void setupHumiditySensorData() {
        // remember first item's timestamp - referenceTimestamp
        long referenceTimestamp = (new Timestamp(humidityData.get(0).getDate().getTime())).getTime();

        List<Entry> entries = new ArrayList<>(humidityData.size() + 1);
        Entry e;
        for (int i = 0; i < humidityData.size(); i++) {
            SensorData sensorEntry = humidityData.get(i);
            //convert SensorData timestamps to short timestamps
            float Xnew = (new Timestamp(sensorEntry.getDate().getTime())).getTime() - referenceTimestamp;
            e = new Entry(Xnew, sensorEntry.getValue());
            entries.add(e);
        }

        // setup chart
        LineDataSet lineDataSet = new LineDataSet(entries, "Daily average");
        lineDataSet.setCircleRadius(2);
        LineData lineData = new LineData(lineDataSet);
        // set value formatter for x axis to show dates
        ValueFormatter xAxisFormatter = new DateAxisValueFormatter(referenceTimestamp);
        XAxis xAxis = movementLineChart.getXAxis();
        xAxis.setValueFormatter(xAxisFormatter);
        // stile the chart
        humidityLineChart.getAxisLeft().setTextColor(Color.WHITE);
        humidityLineChart.getXAxis().setTextColor(Color.WHITE);
        humidityLineChart.getAxisRight().setEnabled(false);
        int bgColor = getResources().getColor(R.color.transparent);
        humidityCharts.setBackgroundColor(bgColor);
        // set data and notify the chart
        humidityLineChart.setData(lineData);
        humidityLineChart.invalidate();
    }
}
