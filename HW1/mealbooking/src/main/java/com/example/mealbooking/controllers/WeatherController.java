package com.example.mealbooking.controllers;

import com.example.mealbooking.services.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/cache-stats")
    public Map<String, Integer> getStats() {
        return weatherService.getCacheStats();
    }
}
