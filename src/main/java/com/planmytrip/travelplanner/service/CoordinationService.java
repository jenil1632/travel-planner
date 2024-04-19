package com.planmytrip.travelplanner.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.planmytrip.travelplanner.model.*;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CoordinationService {

    private final WeatherApiService weatherApiService;

    private final GeminiApiService geminiApiService;

    private final PhotoService photoService;

    CoordinationService(WeatherApiService weatherApiService, GeminiApiService geminiApiService, PhotoService photoService) {
        this.weatherApiService = weatherApiService;
        this.geminiApiService = geminiApiService;
        this.photoService = photoService;
    }

    public ItinResponse buildItinResponse(ItinRequest itinRequest) {
        GenerateContentResponse llmResp = geminiApiService.makeApiCall(itinRequest);

        String jsonString = extractLLMResponse(llmResp);
        System.out.println(jsonString);

        List<ItineraryByDay> itineraryByDays = convertLlmJsonResponseToItineraryList(jsonString);

        List<String> urls =fetchPhotoURLs(itineraryByDays);

        WeatherData weatherData = weatherApiService.fetchWeatherData(itinRequest.getDestination());


        ItinResponse itinResponse = new ItinResponse();
        itinResponse.setUrls(urls);
        itinResponse.setItineraryByDays(itineraryByDays);
        itinResponse.setWeatherData(weatherData);

        return itinResponse;
    }

    private String extractLLMResponse(GenerateContentResponse generateContentResponse) {
        return generateContentResponse.getCandidates().get(0).getContent().getParts().get(0).getText();
    }

    private List<ItineraryByDay> convertLlmJsonResponseToItineraryList(String jsonString) {
        int startIndex = jsonString.indexOf('[');
        int lastIndex = jsonString.lastIndexOf(']');
        jsonString = jsonString.substring(startIndex, lastIndex+1);

        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<ItineraryByDay>>() {}.getType();

        return gson.fromJson(jsonString, collectionType);
    }

    private List<String> fetchPhotoURLs(List<ItineraryByDay> itineraryByDays) {
        List<CompletableFuture<String>> futureList = itineraryByDays.stream()
                .map(itin -> CompletableFuture.supplyAsync(() -> photoService.getPhotoUrls(itin.getHighlight()))).toList();

        return futureList.stream().map(CompletableFuture::join).toList();
    }
}
