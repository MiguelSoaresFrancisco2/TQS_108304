package com.example.mealbooking.repository;

import com.example.mealbooking.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    // Podemos adicionar consultas personalizadas mais tarde, se necessário
}
