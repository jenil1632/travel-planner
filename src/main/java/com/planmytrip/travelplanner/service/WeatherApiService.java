package com.planmytrip.travelplanner.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.planmytrip.travelplanner.model.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;

@Service
public class WeatherApiService {

    @Value("${open-weather-api-key}")
    private String apiKey;

    @Autowired
    private  RestTemplate restTemplate;

    public WeatherData fetchWeatherData(String place) {

        Coordinates coordinates = fetchLocationCoordinates(place);

        JsonObject weatherJson = fetchWeatherFromApi(coordinates);

        double temp = extractTemperatureFromJson(weatherJson);
        double humidity = extractHumidityFromJson(weatherJson);
        String summary = extractWeatherDescriptionFromJson(weatherJson);
        String icon = extractWeatherIconFromJson(weatherJson);


        DecimalFormat df = new DecimalFormat("0.00");
        return new WeatherData(
                Double.parseDouble(df.format(temp)),
                Double.parseDouble(df.format(humidity)),
                summary,
                icon);
    }

    private Coordinates fetchLocationCoordinates(String place) {

        String resourceUrl = "http://api.openweathermap.org/geo/1.0/direct?q="+place+"&limit=1&appid="+apiKey;

        String resp = restTemplate.getForObject(resourceUrl, String.class);

        JsonArray jsonArray = JsonParser.parseString(resp).getAsJsonArray();
        double lat = jsonArray.get(0).getAsJsonObject().get("lat").getAsDouble();
        double lon = jsonArray.get(0).getAsJsonObject().get("lon").getAsDouble();

        return new Coordinates(lat, lon);
    }

    private JsonObject fetchWeatherFromApi(Coordinates coordinates) {

        double lat = coordinates.getLat();
        double lon = coordinates.getLon();

        String resourceUrl = "https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&appid="+apiKey;

        String resp = restTemplate.getForObject(resourceUrl, String.class);

        return JsonParser.parseString(resp).getAsJsonObject();
    }

    private double extractTemperatureFromJson(JsonObject json) {
        return json.getAsJsonObject("main").get("temp").getAsDouble() - 273; // in Celsius
    }

    private double extractHumidityFromJson(JsonObject json) {
        return json.getAsJsonObject("main").get("humidity").getAsDouble(); // in percentage
    }

    private String extractWeatherDescriptionFromJson(JsonObject json) {
        return json.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
    }

    private String extractWeatherIconFromJson(JsonObject json) {
        return json.getAsJsonArray("weather").get(0).getAsJsonObject().get("icon").getAsString();
    }


    private static class Coordinates {
        private final double lat;
        private final double lon;

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }

        public Coordinates(double lat, double lon) {
            this.lat = lat;
            this.lon = lon;
        }
    }


}
