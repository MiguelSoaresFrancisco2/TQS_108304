package com.example.mealbooking.services;

import com.example.mealbooking.models.Meal;
import com.example.mealbooking.models.Restaurant;
import com.example.mealbooking.repositories.MealRepository;
import com.example.mealbooking.repositories.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final RestaurantRepository restaurantRepository;
    private final WeatherService weatherService;

    public List<Meal> getUpcomingMeals(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return mealRepository.findByRestaurantAndDateAfter(restaurant, LocalDate.now().minusDays(1));
    }

    public String getWeatherForDate(LocalDate date) {
        return weatherService.getForecast(date);
    }
}
