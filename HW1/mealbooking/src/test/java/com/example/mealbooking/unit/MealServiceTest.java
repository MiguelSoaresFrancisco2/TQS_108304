package com.example.mealbooking.unit;

import com.example.mealbooking.models.Meal;
import com.example.mealbooking.models.Restaurant;
import com.example.mealbooking.repositories.MealRepository;
import com.example.mealbooking.repositories.RestaurantRepository;
import com.example.mealbooking.services.MealService;
import com.example.mealbooking.services.WeatherService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MealServiceTest {

    MealRepository mealRepo = mock(MealRepository.class);
    RestaurantRepository restaurantRepo = mock(RestaurantRepository.class);
    WeatherService weatherService = mock(WeatherService.class);

    MealService service = new MealService(mealRepo, restaurantRepo, weatherService);

    @Test
    void testGetUpcomingMealsReturnsMealsFromRestaurant() {
        Restaurant rest = Restaurant.builder().id(1L).name("Cantina").build();
        List<Meal> meals = List.of(
                Meal.builder().name("Arroz").date(LocalDate.now().plusDays(1)).restaurant(rest).build()
        );

        when(restaurantRepo.findById(1L)).thenReturn(Optional.of(rest));
        when(mealRepo.findByRestaurantAndDateAfter(eq(rest), any()))
                .thenReturn(meals);

        List<Meal> result = service.getUpcomingMeals(1L);

        assertEquals(1, result.size());
        assertEquals("Arroz", result.get(0).getName());
    }

    @Test
    void testGetWeatherForDateCallsWeatherService() {
        LocalDate date = LocalDate.now().plusDays(1);
        when(weatherService.getForecast(date)).thenReturn("Chuvoso");

        String forecast = service.getWeatherForDate(date);

        assertEquals("Chuvoso", forecast);
        verify(weatherService).getForecast(date);
    }
}
