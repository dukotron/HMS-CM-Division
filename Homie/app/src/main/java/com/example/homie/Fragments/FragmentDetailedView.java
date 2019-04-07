package com.example.homie.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.homie.R;

public class FragmentDetailedView extends Fragment {

    View v;
    RecyclerView recyclerView;

    public FragmentDetailedView(){


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        v = inflater.inflate(R.layout.detailed_view_fragments,container,false);


        return v;


    }

}
