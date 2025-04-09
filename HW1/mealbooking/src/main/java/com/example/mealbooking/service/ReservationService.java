package com.example.mealbooking.service;

import com.example.mealbooking.model.Meal;
import com.example.mealbooking.model.Reservation;
import com.example.mealbooking.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private MealService mealService;  // Injeção do MealService

    // Criar uma nova reserva com restaurantId
    public Reservation createReservation(String reservationCode, Long mealId, String mealName, String dayOfWeek, Long restaurantId) {
        Reservation reservation = new Reservation();
        Meal meal = mealService.getMealById(mealId);  // Obtém a refeição pelo ID
        meal.setReserved(true);  // Marca a refeição como reservada
        reservation.setMeal(meal);  // Atribui a refeição à reserva
        reservation.setReservationCode(reservationCode);
        reservation.setMealName(mealName);
        reservation.setDayOfWeek(dayOfWeek); // Associando o dia da semana
        reservation.setRestaurantId(restaurantId); // Associando o ID do restaurante
    
        mealService.saveMeal(meal);  // Atualiza a refeição no banco de dados
        return reservationRepository.save(reservation);  // Usa o repositório para salvar a reserva
    }

    // Buscar uma reserva por ID
    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    // Marcar uma reserva como concluída (usada)
    public void markAsUsed(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        reservation.ifPresent(res -> {
            res.setUsed(true);
            reservationRepository.save(res);
            
            // Após marcar como usada, também podemos atualizar o status da refeição
            Meal meal = res.getMeal();
            meal.setReserved(false);  // Desmarca a refeição como reservada
            mealService.saveMeal(meal);  // Atualiza a refeição no banco de dados
        });
    }

    // Remover uma reserva
    public void delete(Reservation reservation) {
        reservationRepository.delete(reservation);  // Exclui a reserva do banco de dados
    }

    // Buscar todas as reservas
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    // Buscar reservas para uma refeição e dia específico
    public List<Reservation> findReservationsByMealAndDay(Meal meal, String dayOfWeek) {
        return reservationRepository.findByMealAndDayOfWeek(meal, dayOfWeek);
    }

    // Salvar uma reserva
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }
}
