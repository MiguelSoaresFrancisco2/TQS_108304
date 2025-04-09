package com.example.mealbooking.controller;

import com.example.mealbooking.model.Meal;
import com.example.mealbooking.model.Reservation;
import com.example.mealbooking.service.MealService;
import com.example.mealbooking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StaffController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MealService mealService;

    // Endpoint para a página de "Staff", exibe as reservas
    @GetMapping("/staff")
    public String viewReservations(Model model) {
        // Buscar todas as reservas
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "staff";  // Página HTML para visualizar as reservas
    }

    // Método para marcar a reserva como concluída
    @PostMapping("/staff/markAsCompleted")
    public String markAsCompleted(@RequestParam Long reservationId) {
        // Buscar a reserva pelo ID
        Reservation reservation = reservationService.getReservationById(reservationId).orElse(null);
        
        if (reservation != null) {
            // Marca a reserva como concluída
            reservation.setUsed(true);
            reservationService.saveReservation(reservation);  // Usa o save do repositório

            // Desmarcar a refeição associada como reservada
            Meal meal = reservation.getMeal();
            meal.setReserved(false);  // Marca a refeição como não reservada
            mealService.saveMeal(meal);  // Atualiza a refeição no banco de dados
        }

        return "redirect:/staff";  // Redireciona para a página de Staff após marcar
    }
}
