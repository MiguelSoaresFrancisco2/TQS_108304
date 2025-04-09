package com.example.mealbooking.controller;

import com.example.mealbooking.model.Meal;
import com.example.mealbooking.model.Restaurant;
import com.example.mealbooking.service.MealService;
import com.example.mealbooking.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/restaurant")
public class MealController {

    @Autowired
    private MealService mealService;

    @Autowired
    private RestaurantService restaurantService;

    // Endpoint para obter refeições de um restaurante por dia da semana
    @GetMapping("/{restaurantId}/meals/day/{dayOfWeek}")
    public String getMealsByDay(@PathVariable Long restaurantId, @PathVariable String dayOfWeek, Model model) {
        // Buscar o restaurante e suas refeições para o dia específico
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        List<Meal> meals = mealService.getMealsByRestaurantAndDay(restaurantId, dayOfWeek);

        // Adicionar atributos para a view
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("meals", meals);
        model.addAttribute("dayOfWeek", dayOfWeek);

        // Retornar a página Thymeleaf para renderização
        return "restaurant-meals"; // Nome da sua página Thymeleaf
    }
}
