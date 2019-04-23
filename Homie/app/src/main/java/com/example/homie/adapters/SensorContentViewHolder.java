package com.example.homie.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homie.R;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class SensorContentViewHolder extends ChildViewHolder {

    private ImageView chart;
    private TextView value;
    //TODO layout for sensor content
    public SensorContentViewHolder(@NonNull View itemView) {
        super(itemView);
        chart = itemView.findViewById(R.id.graph);
        value = itemView.findViewById(R.id.value);
    }

    public void setValue(String title) {
        value.setText(title);
    }
}
