package com.example.mealbooking.service;

import com.example.mealbooking.model.Meal;
import com.example.mealbooking.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    // Método para obter todas as refeições
    public List<Meal> getAllMeals() {
        return mealRepository.findAll();
    }

    // Método para obter uma refeição específica por ID
    public Meal getMealById(Long id) {
        return mealRepository.findById(id).orElse(null);
    }

    // Método para criar uma nova refeição
    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    // Método para obter refeições de um restaurante por dia da semana
    public List<Meal> getMealsByRestaurantAndDay(Long restaurantId, String dayOfWeek) {
        // Aqui, você pode realizar uma consulta no repositório para buscar refeições
        // filtradas pelo `restaurantId` e `dayOfWeek` (isso pode ser feito usando a consulta personalizada no repositório).
        return mealRepository.findMealsByRestaurantIdAndDayOfWeek(restaurantId, dayOfWeek);
    }
    
}
