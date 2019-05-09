package com.example.homie.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Device extends ExpandableGroup<CurrentData> {

    private String id;

    public Device(String title, String id, List<CurrentData> items) {
        super(title, items);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
