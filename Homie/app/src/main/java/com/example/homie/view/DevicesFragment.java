package com.example.homie.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.example.homie.adapters.DevicesAdapter;
import com.example.homie.model.CurrentData;
import com.example.homie.model.Device;
import com.example.homie.R;

import java.util.ArrayList;
import java.util.List;

public class DevicesFragment extends Fragment {

    private RecyclerView myRecyclerView;
    private DevicesAdapter adapter;
    private List<Device> devices;

    //TODO viewModel class for the fragment

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getDevicesInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_devices_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycleView(view);

        Button clear = view.findViewById(R.id.toggle_button);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.toggleGroup(devices.get(0));
            }
        });
    }

    private void getDevicesInfo(){
        //TODO get devices info logic code
        devices = new ArrayList<>();
        CurrentData data = new CurrentData("CO2","322");
        ArrayList<CurrentData> a = new ArrayList<>();
        a.add(data);
        Device device = new Device("Hallway",a);
        devices.add(device);
        data = new CurrentData("Temperature","22");
        a = new ArrayList<>();
        a.add(data);
        device = new Device("Living",a);
        devices.add(device);
        data = new CurrentData("CO2","122");
        a = new ArrayList<>();
        a.add(data);
        device = new Device("Bedroom",a);
        devices.add(device);
        /*
        devices.add(new Device("Bedroom", "32w" ));
        devices.add(new Device("Kitchen", "123wr" ));
        devices.add(new Device("Hallway", "123");
        devices.add(new Device("Bathroom", "213fds" ));
        devices.add(new Device("Office", "123x" ));
        devices.add(new Device("Living Room", "2313ng" ));
        devices.add(new Device("Living Room", "13yt" ));
        devices.add(new Device("Living Room", "32h" ));
        devices.add(new Device("Kitchen", "43fe" ));
        devices.add(new Device("Hallway", "54f" ));
        devices.add(new Device("Bathroom", "5e" ));
        devices.add(new Device("Office", "fg" ));
        devices.add(new Device("Kitchen", "t34" ));
        devices.add(new Device("Hallway", "2dr" ));
        devices.add(new Device("Bathroom", "43er" ));
        devices.add(new Device("Office", "343e" ));
        */
    }

    private void initRecycleView(View view){
        myRecyclerView = view.findViewById(R.id.recycler_view_devices_list);
        adapter = new DevicesAdapter(devices);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(adapter);
    }
}
