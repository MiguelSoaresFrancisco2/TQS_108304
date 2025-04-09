package com.example.mealbooking.controllers;

import com.example.mealbooking.models.Meal;
import com.example.mealbooking.services.MealService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @GetMapping("/meals-home")  // Alterado de "/" para "/meals-home"
    public String home() {
        return "index";
    }

    @GetMapping("/meals")
    public String listMeals(@RequestParam Long restaurantId, Model model) {
        List<Meal> meals = mealService.getUpcomingMeals(restaurantId);
        model.addAttribute("meals", meals);
        model.addAttribute("weatherService", mealService); // usado para chamar getWeatherForDate no Thymeleaf
        return "meals";
    }
}

