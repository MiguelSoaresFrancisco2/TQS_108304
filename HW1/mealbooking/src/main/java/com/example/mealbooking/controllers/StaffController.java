package com.example.mealbooking.controllers;

import com.example.mealbooking.services.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class StaffController {

    private final ReservationService reservationService;

    @GetMapping("/staff")
    public String staffPage() {
        return "staff";
    }

    @PostMapping("/checkin")
    public String checkin(@RequestParam String code, Model model) {
        boolean ok = reservationService.checkIn(code);
        model.addAttribute("result", ok ? "Reserva validada!" : "Código inválido ou já usado.");
        return "staff";
    }
}
