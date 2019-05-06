package com.example.homie.adapters;

import android.view.View;
import android.widget.TextView;

import com.example.homie.R;
import com.example.homie.models.CurrentData;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

public class CurrentDataViewHolder extends ChildViewHolder {

    private TextView dataType;
    private TextView dataValue;

    public CurrentDataViewHolder(View itemView) {
        super(itemView);
        dataType = itemView.findViewById(R.id.list_item_current_data_type);
        dataValue = itemView.findViewById(R.id.list_item_current_data_value);
    }

    public void setDataType(String type){
        dataType.setText(type);
    }

    public void setDataValue(String value){
        dataValue.setText(value);
    }

    public void onBind(CurrentData currentData) {
        dataType.setText(currentData.getDataType());
        dataValue.setText(currentData.getDataValue());
    }
}
