package com.example.homie.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homie.R;
import com.example.homie.model.Sensor;
import com.example.homie.model.SensorContent;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class SensorsAdapter extends ExpandableRecyclerViewAdapter<SensorViewHolder, SensorContentViewHolder> {

    public SensorsAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }
    @Override
    public SensorViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_sensor, parent, false);
        return new SensorViewHolder(view);
    }

    @Override
    public SensorContentViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_sensor_content, parent, false);
        return new SensorContentViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(SensorContentViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {
        final SensorContent sensorContent = ((Sensor) group).getItems().get(childIndex);
        holder.setValue(sensorContent.getType());
    }

    @Override
    public void onBindGroupViewHolder(SensorViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setSensorTitle(group);
    }
}
