package com.example.mealbooking.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id @GeneratedValue
    private Long id;

    private String code; // token gerado

    private boolean used;

    @ManyToOne
    private Meal meal;
}
