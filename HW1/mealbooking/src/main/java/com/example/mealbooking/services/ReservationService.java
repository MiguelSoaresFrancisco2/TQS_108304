package com.example.mealbooking.services;

import com.example.mealbooking.models.Meal;
import com.example.mealbooking.models.Reservation;
import com.example.mealbooking.repositories.MealRepository;
import com.example.mealbooking.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final MealRepository mealRepository;

    public Reservation createReservation(Long mealId) {
        Meal meal = mealRepository.findById(mealId)
                .orElseThrow(() -> new RuntimeException("Meal not found"));

        String code = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Reservation reservation = Reservation.builder()
                .meal(meal)
                .code(code)
                .used(false)
                .build();

        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> getByCode(String code) {
        return reservationRepository.findByCode(code);
    }

    public boolean checkIn(String code) {
        Reservation res = reservationRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (res.isUsed()) return false;

        res.setUsed(true);
        reservationRepository.save(res);
        return true;
    }
}
