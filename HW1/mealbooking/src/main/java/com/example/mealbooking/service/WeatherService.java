package com.example.mealbooking.service;

import com.example.mealbooking.model.WeatherForecast;
import com.example.mealbooking.repository.WeatherForecastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WeatherService {

    @Autowired
    private WeatherForecastRepository weatherForecastRepository;

    // Método para obter a previsão do tempo com cache
    @Cacheable(value = "weatherCache", key = "#date")
    public String getWeatherForecast(LocalDate date) {
        // Converte o valor retornado de getDayOfYear() de int para Long
        Long dayOfYear = (long) date.getDayOfYear(); 

        // Busca no banco de dados a previsão para a data
        WeatherForecast forecast = weatherForecastRepository.findById(dayOfYear).orElse(null);
        
        if (forecast != null) {
            return forecast.getForecast(); // Retorna a previsão encontrada
        }
        
        return "Previsão não encontrada"; // Caso não exista previsão para a data
    }
}
