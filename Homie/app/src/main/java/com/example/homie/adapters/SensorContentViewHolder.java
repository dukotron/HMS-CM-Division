package com.example.homie.adapters;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.homie.R;

public class SensorContentViewHolder extends ChildViewHolder {

    public ImageView graph;
    public TextView value;

    public SensorContentViewHolder(@NonNull View itemView) {
        super(itemView);
        graph = (ImageView) itemView.findViewById(R.id.graph);
        value = (TextView) itemView.findViewById(R.id.value);
    }
}
