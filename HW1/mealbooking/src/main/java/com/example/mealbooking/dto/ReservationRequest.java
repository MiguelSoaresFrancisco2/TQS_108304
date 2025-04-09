package com.example.mealbooking.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReservationRequest {
    
    @NotNull(message = "O ID da refeição é obrigatório")
    private Long mealId;
}
