package com.example.mealbooking.repository;

import com.example.mealbooking.model.Reservation;
import com.example.mealbooking.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByMealAndDayOfWeek(Meal meal, String dayOfWeek);
    List<Reservation> findAll();
}
