package com.example.mealbooking.controller;

import com.example.mealbooking.model.Meal;
import com.example.mealbooking.model.Reservation;
import com.example.mealbooking.service.MealService;
import com.example.mealbooking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MealService mealService;

    @PostMapping("/reservations")
    public String createReservation(@RequestParam String reservationCode,
                                    @RequestParam Long mealId,
                                    @RequestParam String mealName,
                                    @RequestParam String dayOfWeek,
                                    @RequestParam Long restaurantId,
                                    Model model) {

        // Criação da nova reserva
        Meal meal = mealService.getMealById(mealId);

        // Verifica se já existe uma reserva para essa refeição e dia
        List<Reservation> existingReservations = reservationService.findReservationsByMealAndDay(meal, dayOfWeek);
        
        if (existingReservations.isEmpty()) {
            reservationService.createReservation(reservationCode, mealId, mealName, dayOfWeek, restaurantId);
            return "redirect:/restaurant/" + restaurantId + "/meals/day/" + dayOfWeek;
        }

        // Se já existir, mostrar mensagem de erro
        model.addAttribute("errorMessage", "A refeição já está reservada para esse dia!");
        return "redirect:/restaurant/" + restaurantId + "/meals/day/" + dayOfWeek;
    }

    // Método para cancelar a reserva
    @PostMapping("/reservations/cancelReservation")
    public String cancelReservation(@RequestParam Long reservationId, @RequestParam Long restaurantId, @RequestParam String dayOfWeek) {
        System.out.println("Cancelando reserva com ID: " + reservationId);
        
        // Buscar a reserva pelo ID
        Reservation reservation = reservationService.getReservationById(reservationId).orElse(null);
        
        if (reservation != null) {
            // Excluir a reserva do banco de dados
            reservationService.delete(reservation);
            
            // Buscar a refeição associada à reserva
            Meal meal = reservation.getMeal();
            
            // Marcar a refeição como não reservada
            meal.setReserved(false);
            mealService.saveMeal(meal);  // Atualiza a refeição no banco de dados
        }
        
        return "redirect:/restaurant/" + restaurantId + "/meals/day/" + dayOfWeek;
    }

    
    
    
}
