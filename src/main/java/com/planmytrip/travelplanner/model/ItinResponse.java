package com.planmytrip.travelplanner.model;

import java.util.List;

public class ItinResponse {

    private WeatherData weatherData;
    private List<ItineraryByDay> itineraryByDays;
    private List<String> urls;

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public List<ItineraryByDay> getItineraryByDays() {
        return itineraryByDays;
    }

    public void setItineraryByDays(List<ItineraryByDay> itineraryByDays) {
        this.itineraryByDays = itineraryByDays;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
