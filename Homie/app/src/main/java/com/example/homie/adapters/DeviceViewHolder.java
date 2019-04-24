package com.example.homie.adapters;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.homie.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class DeviceViewHolder extends GroupViewHolder {

    private TextView deviceTitle;
    private Button arrow;

    public DeviceViewHolder(View itemView) {
        super(itemView);
        deviceTitle = itemView.findViewById(R.id.list_item_device_title);
        arrow = itemView.findViewById(R.id.list_item_device_arrow);
    }

    public void setDeviceTitle(ExpandableGroup group){
        deviceTitle.setText(group.getTitle());
    }

    public Button getArrow(){
        return arrow;
    }

    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
