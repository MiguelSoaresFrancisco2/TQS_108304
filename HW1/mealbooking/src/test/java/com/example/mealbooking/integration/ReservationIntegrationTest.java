package com.example.mealbooking.integration;

import com.example.mealbooking.models.Meal;
import com.example.mealbooking.models.Reservation;
import com.example.mealbooking.repositories.MealRepository;
import com.example.mealbooking.repositories.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ReservationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    private Long mealId;

    @BeforeEach
    void setUp() {
        reservationRepository.deleteAll();
        mealRepository.deleteAll();

        Meal meal = Meal.builder()
                .name("Feijoada")
                .date(LocalDate.now().plusDays(1))
                .build();
        meal = mealRepository.save(meal);
        mealId = meal.getId();
    }

    @Test
    void testReservationViaHttp() throws Exception {
        mockMvc.perform(post("/reserve")
                .param("mealId", mealId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("O seu código de reserva é")));
    }
}
