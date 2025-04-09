package com.example.mealbooking.repository;

import com.example.mealbooking.model.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherForecastRepository extends JpaRepository<WeatherForecast, Long> {
    // Aqui você pode adicionar consultas personalizadas, por exemplo:
    // WeatherForecast findByDate(LocalDate date);
}
