package com.example.homie.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.homie.R;
import com.example.homie.adapters.SensorsAdapter;
import com.example.homie.viewModel.DetailedSensorViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
    //TODO delete the class
public class DetailedSensorFragment extends Fragment {

    private DetailedSensorViewModel mViewModel;
    private RecyclerView recyclerView;
    private View view;

    public static DetailedSensorFragment newInstance() {
        return new DetailedSensorFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.detailed_sensor_fragment, container, false);
        recyclerView = view.findViewById(R.id.erv);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(DetailedSensorViewModel.class);
        initAdapter();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        ((SensorsAdapter) Objects.requireNonNull(recyclerView.getAdapter())).onSaveInstanceState(outState);
    }

    private void initAdapter() {
        SensorsAdapter adapter = new SensorsAdapter(view.getContext(), initData());
        adapter.setParentClickableViewAnimationDefaultDuration();
        adapter.setParentAndIconExpandOnClick(true);
    }

    private List<ParentObject> initData() {
        return new ArrayList<ParentObject>();
        //get data from view model class
        //parent object is just title of sensor
        //child object is graph and (text) current value
    }

}
