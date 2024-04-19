package com.planmytrip.travelplanner.controller;

import com.planmytrip.travelplanner.model.GenerateContentResponse;
import com.planmytrip.travelplanner.model.ItinRequest;
import com.planmytrip.travelplanner.model.ItinResponse;
import com.planmytrip.travelplanner.model.WeatherData;
import com.planmytrip.travelplanner.service.CoordinationService;
import com.planmytrip.travelplanner.service.GeminiApiService;
import com.planmytrip.travelplanner.service.PhotoService;
import com.planmytrip.travelplanner.service.WeatherApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ApiController {

    @Autowired
    CoordinationService coordinationService;

    @PostMapping("/itin")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ItinResponse> testApi(@RequestBody ItinRequest itinRequest) {

        return new ResponseEntity<>(coordinationService.buildItinResponse(itinRequest), HttpStatus.OK);

    }
}
