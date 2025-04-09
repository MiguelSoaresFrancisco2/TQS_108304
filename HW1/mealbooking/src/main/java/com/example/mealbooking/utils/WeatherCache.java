package com.example.mealbooking.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class WeatherCache {

    private final Map<LocalDate, CachedForecast> cache = new HashMap<>();
    private final long ttlMinutes;

    public WeatherCache(long ttlMinutes) {
        this.ttlMinutes = ttlMinutes;
    }

    public String get(LocalDate date) {
        CachedForecast cached = cache.get(date);
        if (cached != null && !cached.isExpired(ttlMinutes)) {
            return cached.getForecast();
        }
        return null;
    }

    public void put(LocalDate date, String forecast) {
        cache.put(date, new CachedForecast(forecast, LocalDateTime.now()));
    }

    public boolean has(LocalDate date) {
        return get(date) != null;
    }

    @Data
    @AllArgsConstructor
    private static class CachedForecast {
        private String forecast;
        private LocalDateTime timestamp;

        public boolean isExpired(long ttlMinutes) {
            return timestamp.plusMinutes(ttlMinutes).isBefore(LocalDateTime.now());
        }
    }
}
