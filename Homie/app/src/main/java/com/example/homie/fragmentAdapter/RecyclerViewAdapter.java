package com.example.homie.fragmentAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.homie.Device;
import com.example.homie.R;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    Context myContext;
    List<Device> myData;

    public RecyclerViewAdapter(Context myContext, List<Device> myData) {
        this.myContext = myContext;
        this.myData = myData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(myContext).inflate(R.layout.item_device,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tv_location.setText(myData.get(position).getLocation());
        holder.tv_status.setText(myData.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return myData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView tv_status;
        private TextView tv_location;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_location = (TextView) itemView.findViewById(R.id.location_device);
            tv_status = (TextView) itemView.findViewById(R.id.status_device);
        }
    }

}
