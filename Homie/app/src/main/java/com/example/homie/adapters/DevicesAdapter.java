package com.example.homie.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homie.R;
import com.example.homie.model.CurrentData;
import com.example.homie.model.Device;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class DevicesAdapter extends ExpandableRecyclerViewAdapter<DeviceViewHolder,CurrentDataViewHolder> {

    public DevicesAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    @Override
    public DeviceViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_device,parent,false);
        return new DeviceViewHolder(view);
    }

    @Override
    public CurrentDataViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_current_data,parent,false);
        return new CurrentDataViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(CurrentDataViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {
        final CurrentData currentData = ((Device)group).getItems().get(childIndex);
        holder.setDataType(currentData.getDataType());
        holder.setDataValue(currentData.getDataValue());
    }

    @Override
    public void onBindGroupViewHolder(DeviceViewHolder holder, int flatPosition,
                                      ExpandableGroup group) {
        holder.setDeviceTitle(group);
    }
}
