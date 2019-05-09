package com.example.homie.adapters;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homie.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class DeviceViewHolder extends GroupViewHolder {

    private TextView deviceTitle;
    private ImageButton arrow;
    private ImageButton delete;

    public DeviceViewHolder(View itemView) {
        super(itemView);
        deviceTitle = itemView.findViewById(R.id.list_item_device_title);
        arrow = itemView.findViewById(R.id.list_item_device_arrow);
        delete = itemView.findViewById(R.id.list_item_device_delete);
    }

    public void setDeviceTitle(ExpandableGroup group){
        deviceTitle.setText(group.getTitle());
    }

    public ImageButton getArrow(){
        return arrow;
    }

    public ImageButton getDelete(){
        return delete;
    }

}
