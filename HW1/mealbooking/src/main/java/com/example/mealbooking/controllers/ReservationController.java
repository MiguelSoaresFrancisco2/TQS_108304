package com.example.mealbooking.controllers;

import com.example.mealbooking.models.Reservation;
import com.example.mealbooking.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("/reserve")
    public String reserve(@RequestParam Long mealId, Model model) {
        Reservation res = reservationService.createReservation(mealId);
        model.addAttribute("reservation", res);
        return "confirmation";
    }

    @GetMapping("/reservation")
    public String checkReservation(@RequestParam String code, Model model) {
        var opt = reservationService.getByCode(code);
        if (opt.isEmpty()) {
            model.addAttribute("error", "Reserva não encontrada");
            return "reservation";
        }
        model.addAttribute("reservation", opt.get());
        return "reservation";
    }
}
