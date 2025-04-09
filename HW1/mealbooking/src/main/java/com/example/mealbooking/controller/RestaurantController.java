package com.example.mealbooking.controller;

import com.example.mealbooking.model.Restaurant;
import com.example.mealbooking.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class RestaurantController {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Página inicial com os cards "Cliente" e "Staff"
    @GetMapping("/")
    public String home() {
        return "home"; // Retorna o template da home page
    }

    // Página de restaurantes, para quando o cliente clicar no card "Cliente"
    @GetMapping("restaurant/list")
    public String listRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        model.addAttribute("restaurants", restaurants);
        return "restaurants"; // Exibe a lista de restaurantes
    }

    // Página de refeições para o restaurante selecionado
    @GetMapping("restaurant/{id}")
    public String viewRestaurantMeals(@PathVariable Long id, Model model) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        model.addAttribute("restaurant", restaurant);

        // Exibir links para os dias da semana sem carregar as refeições inicialmente
        return "restaurant-meals-links"; // Exibe a página com links para os dias da semana
    }
}
