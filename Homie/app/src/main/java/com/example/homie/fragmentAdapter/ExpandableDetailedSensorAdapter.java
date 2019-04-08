package com.example.homie.fragmentAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.homie.R;
import com.example.homie.model.SensorContent;
import com.example.homie.model.SensorTitle;

import java.util.List;

public class ExpandableDetailedSensorAdapter extends ExpandableRecyclerAdapter<SensorTitleViewHolder, SensorContentViewHolder> {
    LayoutInflater inflater;


    public ExpandableDetailedSensorAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
    }


    @Override
    public SensorTitleViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.detailed_device_parent,viewGroup, false);
        return  new SensorTitleViewHolder(view);
    }

    @Override
    public SensorContentViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.detailed_view_child, viewGroup, false);
        return  new SensorContentViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(SensorTitleViewHolder sensorTitleViewHolder, int i, Object o) {
        SensorTitle title = (SensorTitle) o;
        sensorTitleViewHolder.sensorTextView.setText(title.getTitle());

    }

    @Override
    public void onBindChildViewHolder(SensorContentViewHolder sensorContentViewHolder, int i, Object o) {
        SensorContent child= (SensorContent) o;
        sensorContentViewHolder.graph.setImageResource(child.getGraph());
        sensorContentViewHolder.value.setText(child.getcValue());

    }
}
