package com.planmytrip.travelplanner.service;

import com.planmytrip.travelplanner.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class GeminiApiService {

    @Value("${gemini-api-key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public GenerateContentResponse makeApiCall(ItinRequest itinRequest) {
        RestTemplate restTemplate = new RestTemplate();
        String resourceURL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key="+apiKey;


        HttpEntity<GenerateContentRequest> req = new HttpEntity<>(buildRequest(itinRequest));

        return restTemplate.postForObject(resourceURL, req, GenerateContentResponse.class);

    }

    private GenerateContentRequest buildRequest(ItinRequest itinRequest) {

        List<Content> contents = new ArrayList<>();

        Content content = new Content();
        content.setParts(buildParts(itinRequest));
        contents.add(content);

        GenerateContentRequest generateContentRequest = new GenerateContentRequest();
        generateContentRequest.setContents(contents);

        return generateContentRequest;

    }


    private String buildLLMPrompt(ItinRequest itinRequest) {
        return "Suggest a detailed itinerary for " + itinRequest.getDuration() + " for a trip to " +
                itinRequest.getDestination() + " in " + itinRequest.getSeason() + " with " + itinRequest.getTravelType() +
                "While suggesting an activity, also mention a brief reason. Concat the activity and reason in a single phrase" +
                ". Return the output in JSONArray format as such : " +
                " [{ day: 1," +
                " activities: [...]," +
                " highlight: 'highlight place of the day'}," +
                "{ day: 2," +
                " activities : [...]," +
                " highlight: 'highlight place of the day'}]";
    }

    private List<Part> buildParts(ItinRequest itinRequest) {
        List<Part> parts = new ArrayList<>();

        Part part = new Part();
        part.setText(buildLLMPrompt(itinRequest));
        parts.add(part);

        return parts;
    }



}
