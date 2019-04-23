package com.example.homie.model;

import android.os.Parcel;
import android.os.Parcelable;

public class SensorContent implements Parcelable {

    private String type;
    private int chart;


    public SensorContent(String type) {
        this.type = type;
        this.chart = chart;
    }

    protected SensorContent(Parcel in ) {
        type = in .readString();
        chart = in.readInt();
    }

    public String getType() {
        return type;
    }

    public int getChart() {
        return chart;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator < SensorContent > CREATOR = new Creator < SensorContent > () {
        @Override
        public SensorContent createFromParcel(Parcel in ) {
            return new SensorContent( in );
        }

        @Override
        public SensorContent[] newArray(int size) {
            return new SensorContent[size];
        }
    };
}
