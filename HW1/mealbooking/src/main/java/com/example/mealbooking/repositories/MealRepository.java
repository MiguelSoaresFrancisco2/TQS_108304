package com.example.mealbooking.repositories;

import com.example.mealbooking.models.Meal;
import com.example.mealbooking.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findByRestaurantAndDateAfter(Restaurant restaurant, LocalDate date);
}
