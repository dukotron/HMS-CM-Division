package com.example.homie.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homie.R;
import com.example.homie.models.CurrentData;
import com.example.homie.models.Device;
import com.example.homie.viewModels.DevicesViewModel;
import com.example.homie.views.SensorsActivity;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class DevicesAdapter extends ExpandableRecyclerViewAdapter<DeviceViewHolder, CurrentDataViewHolder> {

    private Context context;
    private List<Device> devices;
    private DevicesViewModel viewModel;

    public DevicesAdapter(List<? extends ExpandableGroup> groups, Context context,
                          List<Device> devices, DevicesViewModel viewModel) {
        super(groups);
        this.devices = devices;
        this.context = context;
        this.viewModel = viewModel;
    }

    @Override
    public DeviceViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_device, parent, false);
        return new DeviceViewHolder(view);
    }

    @Override
    public CurrentDataViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_current_data, parent, false);
        return new CurrentDataViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(CurrentDataViewHolder holder, int flatPosition,
                                      ExpandableGroup group, int childIndex) {
        final CurrentData currentData = ((Device) group).getItems().get(childIndex);
        holder.setDataType(currentData.getDataType());
        holder.setDataValue(currentData.getDataValue());
    }

    @Override
    public void onBindGroupViewHolder(final DeviceViewHolder holder, final int flatPosition,
                                      final ExpandableGroup group) {
        holder.setDeviceTitle(group);
        holder.getArrow().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SensorsActivity.class);
                intent.putExtra("deviceTitle", group.getTitle());
                intent.putExtra("deviceId", devices.get(flatPosition).getId());
                context.startActivity(intent);
            }
        });
        holder.getDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteDevice(devices.get(flatPosition).getId());
            }
        });
    }

    @Override
    public boolean onGroupClick(int flatPos) {
        return super.onGroupClick(flatPos);
    }

}
