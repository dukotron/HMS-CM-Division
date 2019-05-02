package com.example.homie.model;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Device extends ExpandableGroup<CurrentData> {

    public Device(String title, List<CurrentData> items) {
        super(title, items);
    }
}
