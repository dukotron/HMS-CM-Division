package com.example.homie.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.homie.adapters.DevicesAdapter;
import com.example.homie.models.Device;
import com.example.homie.R;
import com.example.homie.viewModels.DevicesViewModel;

import java.util.ArrayList;
import java.util.List;

public class DevicesFragment extends Fragment {

    private RecyclerView recyclerView;
    private DevicesAdapter adapter;
    private List<Device> devices;

    private DevicesViewModel viewModel;

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
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        devices = new ArrayList<>();

        viewModel = ViewModelProviders.of(this).get(DevicesViewModel.class);
        viewModel.getShowError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getDevices().observe(this, new Observer<List<Device>>() {
            @Override
            public void onChanged(List<Device> devicesList) {
                devices = new ArrayList<>(devicesList.size() + 1);

                for (int i = 0; i < devicesList.size(); i++) {
                    devices.add(devicesList.get(i));
                }
                initRecycleView(view);
                adapter.notifyDataSetChanged();
            }
        });
        viewModel.getScore().observe(this, new Observer<Float>() {
            @Override
            public void onChanged(Float score) {
                ActionBar actionBar = ((AppCompatActivity) getContext()).getSupportActionBar();
                actionBar.setTitle("Overview - " + score + "/10");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getDevicesInfo();
        getHomeScore();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.options_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.option_add_device) {
            startActivity(new Intent(getContext(), AddDeviceActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getDevicesInfo() {
        viewModel.loadAllDevices();
    }

    private void getHomeScore() {
        viewModel.loadHomeScore();
    }

    private void initRecycleView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_devices_list);
        // weird fix using library
        adapter = new DevicesAdapter(devices, getContext(), devices, viewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
