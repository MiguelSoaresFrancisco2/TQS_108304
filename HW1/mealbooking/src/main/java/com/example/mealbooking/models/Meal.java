package com.example.mealbooking.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Meal {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private LocalDate date;

    @ManyToOne
    private Restaurant restaurant;
}
