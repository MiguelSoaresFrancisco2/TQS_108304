package com.example.mealbooking.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String location;
}
