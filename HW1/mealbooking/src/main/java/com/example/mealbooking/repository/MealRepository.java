package com.example.mealbooking.repository;

import com.example.mealbooking.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal, Long> {

    // Definindo o método para buscar refeições por restaurante e dia da semana
    List<Meal> findMealsByRestaurantIdAndDayOfWeek(Long restaurantId, String dayOfWeek);
    Optional<Meal> findByName(String name);  // Método para buscar refeição pelo nome
}
