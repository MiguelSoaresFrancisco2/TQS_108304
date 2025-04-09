package com.example.mealbooking.service;

import com.example.mealbooking.model.Restaurant;
import com.example.mealbooking.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Método para obter o restaurante por ID
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null); // Retorna null se não encontrar
    }
}
