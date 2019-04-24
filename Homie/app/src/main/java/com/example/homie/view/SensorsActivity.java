package com.example.homie.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.homie.R;
import com.example.homie.adapters.SensorsAdapter;
import com.example.homie.model.Sensor;

import java.util.ArrayList;

public class SensorsActivity extends AppCompatActivity {
    private SensorsAdapter adapter;
    private ArrayList<Sensor> sensors;
    private RecyclerView recyclerView;

    //TODO viewModel class for the activity

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO layout for sensors list
        setContentView(R.layout.activity_sensors_list);

        initToolbar();
        getSensorsInfo();
        initRecycleView();

        Button clear = findViewById(R.id.toggle_button);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.toggleGroup(sensors.get(0));
            }
        });
    }

    private void initToolbar(){
        //TODO init toolbar
    }

    private void initRecycleView(){
        recyclerView = findViewById(R.id.recycler_view_sensors_list);
        adapter = new SensorsAdapter(sensors);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void getSensorsInfo(){
        //TODO get sensors info logic code
        sensors = new ArrayList<>();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        adapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        adapter.onRestoreInstanceState(savedInstanceState);
    }
}