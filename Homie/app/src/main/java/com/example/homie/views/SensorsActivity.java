package com.example.homie.views;

import androidx.appcompat.app.ActionBar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
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
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SensorsActivity extends AppCompatActivity {

    private String deviceTitle;
    private String deviceId;
    private DateFormat dateFormat;

    private TextView movementSensorTitle;
    private EditText movementDateFrom;
    private EditText movementDateTo;
    private Button movementBtn;
    private LinearLayout movementCharts;
    private LineChart movementLineChart;
    private List<ILineDataSet> movementDataSets;

    private TextView coSensorTitle;
    private EditText coDateFrom;
    private EditText coDateTo;
    private Button coBtn;
    private LinearLayout coCharts;
    private BarChart coBarChart;
    private List<IBarDataSet> coDataSets;

    private TextView temperatureSensorTitle;
    private EditText temperatureDateFrom;
    private EditText temperatureDateTo;
    private Button temperatureBtn;
    private LinearLayout temperatureCharts;
    private BarChart temperatureBarChart;
    private List<IBarDataSet> temperatureDataSets;

    private TextView humiditySensorTitle;
    private EditText humidityDateFrom;
    private EditText humidityDateTo;
    private Button humidityBtn;
    private LinearLayout humidityCharts;
    private LineChart humidityLineChart;
    private List<ILineDataSet> humidityDataSets;

    private TextView lightSensorTitle;
    private EditText lightDateFrom;
    private EditText lightDateTo;
    private Button lightBtn;
    private LinearLayout lightCharts;
    private LineChart lightLineChart;
    private List<ILineDataSet> lightDataSets;

    private SensorsViewModel viewModel;

    private String dateToday;
    private String dateWeekAgo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensors_list);

        deviceTitle = getIntent().getExtras().getString("deviceTitle");
        deviceId = getIntent().getExtras().getString("deviceId");
        movementDataSets = new ArrayList<>();
        coDataSets = new ArrayList<>();
        temperatureDataSets = new ArrayList<>();
        humidityDataSets = new ArrayList<>();
        lightDataSets = new ArrayList<>();

        initToolbar();
        initViewModel();

        dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH) + 1; // months getDailyData from 0
        int year = cldr.get(Calendar.YEAR);
        dateToday = day + "-" + month + "-" + year;
        cldr.add(Calendar.DATE, -7);
        day = cldr.get(Calendar.DAY_OF_MONTH);
        month = cldr.get(Calendar.MONTH) + 1;
        year = cldr.get(Calendar.YEAR);
        dateWeekAgo = day + "-" + month + "-" + year;

        initMovementCharts();
        initMovementData();

        initCoCharts();
        initCoData();

        initTemperatureCharts();
        initTemperatureData();

        initHumidityCharts();
        initHumidityData();

        initLightCharts();
        initLightData();
        styleCharts();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(deviceTitle);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(SensorsViewModel.class);
        viewModel.setDeviceId(deviceId);
        viewModel.getMovementDailyData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(@Nullable List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    loadLineChartData(movementLineChart, sensorsData, "Daily average",
                            Color.RED, movementDataSets);
                }
            }
        });

        viewModel.getMovementHourlyData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    loadLineChartData(movementLineChart, sensorsData, "Hourly average",
                            Color.GREEN, movementDataSets);
                }
            }
        });

        viewModel.getCoDailyData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(@Nullable List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    loadBarChartData(coBarChart, sensorsData, "Daily average",
                            Color.RED, coDataSets);
                }
            }
        });

        viewModel.getCoHourlyData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    loadBarChartData(coBarChart, sensorsData, "Hourly average",
                            Color.GREEN, coDataSets);
                }
            }
        });

        viewModel.getTemperatureDailyData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(@Nullable List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    loadBarChartData(temperatureBarChart, sensorsData, "Daily average",
                            Color.RED, temperatureDataSets);
                }
            }
        });

        viewModel.getTemperatureHourlyData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(@Nullable List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    loadBarChartData(temperatureBarChart, sensorsData, "Hourly average",
                            Color.GREEN, temperatureDataSets);
                }
            }
        });

        viewModel.getHumidityDailyData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(@Nullable List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    loadLineChartData(humidityLineChart, sensorsData, "Daily average",
                            Color.RED, humidityDataSets);
                }
            }
        });

        viewModel.getHumidityHourlyData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(@Nullable List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    loadLineChartData(humidityLineChart, sensorsData, "Hourly average",
                            Color.GREEN, humidityDataSets);
                }
            }
        });

        viewModel.getLightDailyData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    loadLineChartData(lightLineChart, sensorsData, "Daily average",
                            Color.RED, lightDataSets);
                }
            }
        });

        viewModel.getLightHourlyData().observe(this, new Observer<List<SensorData>>() {
            @Override
            public void onChanged(List<SensorData> sensorsData) {
                if (sensorsData != null && sensorsData.size() != 0) {
                    loadLineChartData(lightLineChart, sensorsData, "Hourly average",
                            Color.GREEN, lightDataSets);
                }
            }
        });
    }

    private void styleCharts() {
        // style movement chart
        movementLineChart.getAxisLeft().setTextColor(Color.WHITE);
        movementLineChart.getXAxis().setTextColor(Color.WHITE);
        movementLineChart.getAxisRight().setEnabled(false);
        int bgColor = getResources().getColor(R.color.transparent);
        movementCharts.setBackgroundColor(bgColor);

        // style co2 chart
        coBarChart.getAxisLeft().setTextColor(Color.WHITE);
        coBarChart.getXAxis().setTextColor(Color.WHITE);
        coBarChart.getAxisRight().setEnabled(false);
        bgColor = getResources().getColor(R.color.transparent);
        coCharts.setBackgroundColor(bgColor);

        // style temperature chart
        temperatureBarChart.getAxisLeft().setTextColor(Color.WHITE);
        temperatureBarChart.getXAxis().setTextColor(Color.WHITE);
        temperatureBarChart.getAxisRight().setEnabled(false);
        bgColor = getResources().getColor(R.color.transparent);
        temperatureBarChart.setBackgroundColor(bgColor);

        // style humidity chart
        humidityLineChart.getAxisLeft().setTextColor(Color.WHITE);
        humidityLineChart.getXAxis().setTextColor(Color.WHITE);
        humidityLineChart.getAxisRight().setEnabled(false);
        bgColor = getResources().getColor(R.color.transparent);
        humidityCharts.setBackgroundColor(bgColor);

        // style light chart
        lightLineChart.getAxisLeft().setTextColor(Color.WHITE);
        lightLineChart.getXAxis().setTextColor(Color.WHITE);
        lightLineChart.getAxisRight().setEnabled(false);
        bgColor = getResources().getColor(R.color.transparent);
        lightLineChart.setBackgroundColor(bgColor);
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
        movementDateTo = findViewById(R.id.movement_date_to);
        movementDateTo.setText(dateToday);
        movementDateFrom = findViewById(R.id.movement_date_from);
        movementDateFrom.setText(dateWeekAgo);
        movementBtn = findViewById(R.id.movement_btn_show);
        movementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movementDataSets = new ArrayList<>();
                initMovementData();
            }
        });
    }

    private void initMovementData() {

        try {
            Calendar cal = Calendar.getInstance();
            String[] date = movementDateTo.getText().toString().split("-");
            cal.set(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]), 23, 50);
            viewModel.loadMovementData(dateFormat.parse(movementDateFrom.getText().toString()), cal.getTime());
        } catch (ParseException e) {
            Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadLineChartData(LineChart lineChart, List<SensorData> sensorData, String dataName, int color, List<ILineDataSet> dataSets) {
        // remember first item's timestamp - referenceTimestamp
        long referenceTimestamp = (new Timestamp(sensorData.get(0).getDate().getTime())).getTime();

        List<Entry> entries = new ArrayList<>(sensorData.size() + 1);
        Entry e;
        for (int i = 0; i < sensorData.size(); i++) {
            SensorData sensorEntry = sensorData.get(i);
            //convert SensorData timestamps to short timestamps
            float Xnew = (new Timestamp(sensorEntry.getDate().getTime())).getTime() - referenceTimestamp;
            e = new Entry(Xnew, sensorEntry.getValue());
            entries.add(e);
        }

        // setup chart
        LineDataSet lineDataSet = new LineDataSet(entries, dataName);
        lineDataSet.setCircleRadius(2);
        lineDataSet.setColor(color);

        dataSets.add(lineDataSet);
        LineData lineData = new LineData(dataSets);
        // set value formatter for x axis to show dates
        ValueFormatter xAxisFormatter = new DateAxisValueFormatter(referenceTimestamp);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setValueFormatter(xAxisFormatter);

        // set data and notify the chart
        lineChart.setData(lineData);
        lineChart.invalidate();
    }

    private void loadBarChartData(BarChart barChart, List<SensorData> sensorData, String dataName, int color, List<IBarDataSet> dataSets) {
        // remember first item's timestamp - referenceTimestamp
        long referenceTimestamp = (new Timestamp(sensorData.get(0).getDate().getTime())).getTime();

        List<BarEntry> entries = new ArrayList<>(sensorData.size() + 1);
        BarEntry e;
        for (int i = 0; i < sensorData.size(); i++) {
            SensorData sensorEntry = sensorData.get(i);
            //convert SensorData timestamps to short timestamps
            float Xnew = (new Timestamp(sensorEntry.getDate().getTime())).getTime() - referenceTimestamp;
            e = new BarEntry(sensorEntry.getDate().getDay(), sensorEntry.getValue());
            entries.add(e);
        }

        // setup chart
        BarDataSet barDataSet = new BarDataSet(entries, dataName);
        barDataSet.setColor(color);
        dataSets.add(barDataSet);
        BarData barData = new BarData(dataSets);
        // set value formatter for x axis to show dates
        ValueFormatter xAxisFormatter = new DateAxisValueFormatter(referenceTimestamp);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(xAxisFormatter);

        // set data and notify the chart
        barChart.setData(barData);
        barChart.setFitBars(true);
        barChart.invalidate();
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
        coDateTo = findViewById(R.id.co_date_to);
        coDateTo.setText(dateToday);
        coDateFrom = findViewById(R.id.co_date_from);
        coDateFrom.setText(dateWeekAgo);
        coBtn = findViewById(R.id.co_btn_show);
        coBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                coDataSets = new ArrayList<>();
                initCoData();
            }
        });
    }

    private void initCoData() {
        try {
            Calendar cal = Calendar.getInstance();
            String[] date = coDateTo.getText().toString().split("-");
            cal.set(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]), 23, 50);
            viewModel.loadCoData(dateFormat.parse(coDateFrom.getText().toString()), cal.getTime());
        } catch (ParseException e) {
            Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show();
        }
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
        temperatureDateTo = findViewById(R.id.temperature_date_to);
        temperatureDateTo.setText(dateToday);
        temperatureDateFrom = findViewById(R.id.temperature_date_from);
        temperatureDateFrom.setText(dateWeekAgo);
        temperatureBtn = findViewById(R.id.temperature_btn_show);
        temperatureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temperatureDataSets = new ArrayList<>();
                initTemperatureData();
            }
        });
    }

    private void initTemperatureData() {
        try {
            Calendar cal = Calendar.getInstance();
            String[] date = temperatureDateTo.getText().toString().split("-");
            cal.set(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]), 23, 50);
            viewModel.loadTemperatureData(dateFormat.parse(temperatureDateFrom.getText().toString()), cal.getTime());
        } catch (ParseException e) {
            Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show();
        }
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
        humidityDateTo = findViewById(R.id.humidity_date_to);
        humidityDateTo.setText(dateToday);
        humidityDateFrom = findViewById(R.id.humidity_date_from);
        humidityDateFrom.setText(dateWeekAgo);
        humidityBtn = findViewById(R.id.humidity_btn_show);
        humidityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                humidityDataSets = new ArrayList<>();
                initHumidityData();
            }
        });
    }

    private void initHumidityData() {
        try {
            Calendar cal = Calendar.getInstance();
            String[] date = humidityDateTo.getText().toString().split("-");
            cal.set(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]), 23, 50);
            viewModel.loadHumidityData(dateFormat.parse(humidityDateFrom.getText().toString()), cal.getTime());
        } catch (ParseException e) {
            Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private void initLightCharts() {
        lightLineChart = findViewById(R.id.light_sensor_line_chart);
        lightCharts = findViewById(R.id.light_sensor_charts);
        lightSensorTitle = findViewById(R.id.light_sensor);
        lightSensorTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lightCharts.getVisibility() == View.GONE) {
                    lightCharts.setVisibility(View.VISIBLE);
                } else {
                    lightCharts.setVisibility(View.GONE);
                }
            }
        });
        lightDateTo = findViewById(R.id.light_date_to);
        lightDateTo.setText(dateToday);
        lightDateFrom = findViewById(R.id.light_date_from);
        lightDateFrom.setText(dateWeekAgo);
        lightBtn = findViewById(R.id.light_btn_show);
        lightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lightDataSets = new ArrayList<>();
                initLightData();
            }
        });
    }

    private void initLightData() {
        try {
            Calendar cal = Calendar.getInstance();
            String[] date = lightDateTo.getText().toString().split("-");
            cal.set(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]), 23, 50);
            viewModel.loadLightData(dateFormat.parse(lightDateFrom.getText().toString()), cal.getTime());
        } catch (ParseException e) {
            Toast.makeText(this, "Some error occurred", Toast.LENGTH_SHORT).show();
        }
    }

}
