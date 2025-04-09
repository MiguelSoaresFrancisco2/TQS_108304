package com.example.mealbooking.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.mealbooking.utils.WeatherCache;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class WeatherService {

    private final WeatherCache cache = new WeatherCache(30); 
    private final Map<String, Integer> stats = new HashMap<>() {{
        put("total", 0);
        put("hits", 0);
        put("misses", 0);
    }};
    
    private final long TTL_MINUTES = 30;

    public String getForecast(LocalDate date) {
        stats.put("total", stats.get("total") + 1);
    
        if (cache.has(date)) {
            stats.put("hits", stats.get("hits") + 1);
            return cache.get(date);
        }
    
        stats.put("misses", stats.get("misses") + 1);
        String forecast = fetchFromAPI(date);
        cache.put(date, forecast);
        return forecast;
    }
    

    public Map<String, Integer> getCacheStats() {
        return stats;
    }

    private String fetchFromAPI(LocalDate date) {
        log.info("Fetching weather for {}", date);
        return "Sunny"; // ⚠️ trocar depois por chamada à Open-Meteo ou outra API
    }

    @Data
    @AllArgsConstructor
    private static class CachedForecast {
        private String forecast;
        private LocalDateTime timestamp;

        public boolean isExpired() {
            return timestamp.plusMinutes(30).isBefore(LocalDateTime.now());
        }
    }
}
