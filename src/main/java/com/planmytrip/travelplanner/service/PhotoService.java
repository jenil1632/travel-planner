package com.planmytrip.travelplanner.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PhotoService {

    @Value("${unsplash-api-key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public String getPhotoUrls(String place) {

        String resourceURL = "https://api.unsplash.com/photos/random?client_id="+apiKey+"&query="+place;

        String response = restTemplate.getForObject(resourceURL, String.class);

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        return jsonObject.getAsJsonObject("urls").get("small").getAsString();

    }
}
