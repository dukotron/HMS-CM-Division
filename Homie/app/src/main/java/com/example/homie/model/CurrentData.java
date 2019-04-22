package com.example.homie.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CurrentData implements Parcelable {
    private String dataType;
    private String dataValue;

    public CurrentData(String dataName, String dataValue) {
        this.dataType = dataName;
        this.dataValue = dataValue;
    }

    public String getDataType() {
        return dataType;
    }

    public String getDataValue() {
        return dataValue;
    }

    protected CurrentData(Parcel in) {
        dataType = in.readString();
        dataValue = in.readString();
    }

    public static final Creator<CurrentData> CREATOR = new Creator<CurrentData>() {
        @Override
        public CurrentData createFromParcel(Parcel in) {
            return new CurrentData(in);
        }

        @Override
        public CurrentData[] newArray(int size) {
            return new CurrentData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dataType);
        dest.writeString(dataValue);
    }
}
