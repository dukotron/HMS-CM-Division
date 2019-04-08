package com.example.homie.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homie.Device;
import com.example.homie.fragmentAdapter.RecyclerViewAdapter;
import com.example.homie.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentDevices extends Fragment {

    View v;
    private RecyclerView myRecyclerView;
    private List<Device> lstDevice;

    public FragmentDevices() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        v = inflater.inflate(R.layout.devices_fragment, container, false);
        myRecyclerView = v.findViewById(R.id.devices_recyclerview);
        RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), lstDevice);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        myRecyclerView.setAdapter(recyclerAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        lstDevice = new ArrayList<>();
        lstDevice.add(new Device("Living Room", "Active" ));
        lstDevice.add(new Device("Bedroom", "Inactive" ));
        lstDevice.add(new Device("Kitchen", "Active" ));
        lstDevice.add(new Device("Hallway", "Inactiv" ));
        lstDevice.add(new Device("Bathroom", "Active" ));
        lstDevice.add(new Device("Office", "Active" ));
        lstDevice.add(new Device("Living Room", "Active" ));
        lstDevice.add(new Device("Living Room", "Active" ));
        lstDevice.add(new Device("Living Room", "Active" ));
        lstDevice.add(new Device("Kitchen", "Active" ));
        lstDevice.add(new Device("Hallway", "Inactiv" ));
        lstDevice.add(new Device("Bathroom", "Active" ));
        lstDevice.add(new Device("Office", "Active" ));
        lstDevice.add(new Device("Kitchen", "Active" ));
        lstDevice.add(new Device("Hallway", "Inactiv" ));
        lstDevice.add(new Device("Bathroom", "Active" ));
        lstDevice.add(new Device("Office", "Active" ));
    }
}
