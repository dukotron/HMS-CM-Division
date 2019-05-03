package com.example.homie.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

public class Sensor extends ExpandableGroup<SensorContent> {

    private String title;

    public Sensor(String title, List<SensorContent> items) {
        super(title, items);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
