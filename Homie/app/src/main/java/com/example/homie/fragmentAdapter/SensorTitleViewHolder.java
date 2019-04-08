package com.example.homie.fragmentAdapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.homie.R;

public class SensorTitleViewHolder extends ParentViewHolder {

    public TextView sensorTextView;

    public SensorTitleViewHolder(@NonNull View itemView) {
        super(itemView);
        sensorTextView = itemView.findViewById(R.id.sensor_title);
    }
}
