package com.example.homie.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.homie.adapters.DevicesAdapter;
import com.example.homie.models.CurrentData;
import com.example.homie.models.Device;
import com.example.homie.R;

import java.util.ArrayList;
import java.util.List;

public class DevicesFragment extends Fragment {

    private RecyclerView recyclerView;
    private DevicesAdapter adapter;
    private List<Device> devices;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_devices_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDevicesInfo();
        initRecycleView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDevicesInfo();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.option_add_device){
            startActivity(new Intent(getContext(), AddDeviceActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDevicesInfo() {
        //TODO get devices info logic code
        devices = new ArrayList<>();
        CurrentData data = new CurrentData("CO2", "322");
        ArrayList<CurrentData> a = new ArrayList<>();
        a.add(data);
        data = new CurrentData("Temperature", "22");
        a.add(data);
        Device device = new Device("hallway", a);
        devices.add(device);
        data = new CurrentData("Temperature", "22");
        a = new ArrayList<>();
        a.add(data);
        device = new Device("living", a);
        devices.add(device);
        data = new CurrentData("CO2", "122");
        a = new ArrayList<>();
        a.add(data);
        device = new Device("bedroom", a);
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

    private void initRecycleView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_devices_list);
        adapter = new DevicesAdapter(devices,getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
