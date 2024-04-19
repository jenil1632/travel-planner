package com.planmytrip.travelplanner.model;

public class Blob {

    private String mimeTyoe;
    private String data;

    public String getMimeTyoe() {
        return mimeTyoe;
    }

    public void setMimeTyoe(String mimeTyoe) {
        this.mimeTyoe = mimeTyoe;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Blob{" +
                "mimeTyoe='" + mimeTyoe + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
