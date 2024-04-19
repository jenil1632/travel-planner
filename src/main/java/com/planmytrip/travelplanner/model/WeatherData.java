package com.planmytrip.travelplanner.model;

public class WeatherData {

    private double temp;
    private double humidity;
    private String summary;
    private String icon;

    public WeatherData(double temp, double humidity, String summary, String icon) {
        this.temp = temp;
        this.humidity = humidity;
        this.summary = summary;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                ", summary='" + summary + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
