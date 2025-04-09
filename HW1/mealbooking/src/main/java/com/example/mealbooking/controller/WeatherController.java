package com.example.mealbooking.controller;

import com.example.mealbooking.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    // Endpoint para obter a previsão do tempo de um dia específico
    @GetMapping("/{date}")
    public String getWeatherForecast(@PathVariable String date) {
        return weatherService.getWeatherForecast(LocalDate.parse(date));
    }
}
