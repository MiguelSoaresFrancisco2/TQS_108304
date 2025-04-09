package com.example.mealbooking.unit;

import com.example.mealbooking.models.Meal;
import com.example.mealbooking.models.Reservation;
import com.example.mealbooking.repositories.MealRepository;
import com.example.mealbooking.repositories.ReservationRepository;
import com.example.mealbooking.services.ReservationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    MealRepository mealRepo = mock(MealRepository.class);
    ReservationRepository resRepo = mock(ReservationRepository.class);

    ReservationService service = new ReservationService(resRepo, mealRepo);

    @Test
    void testCreateReservationReturnsValidCode() {
        Meal fakeMeal = Meal.builder().id(1L).name("Almoço").date(LocalDate.now()).build();
        when(mealRepo.findById(1L)).thenReturn(Optional.of(fakeMeal));
        when(resRepo.save(any())).thenAnswer(i -> i.getArgument(0));

        Reservation res = service.createReservation(1L);

        assertNotNull(res.getCode());
        assertFalse(res.isUsed());
        assertEquals(fakeMeal, res.getMeal());
    }

    @Test
    void testCheckInSuccess() {
        Reservation fake = Reservation.builder().code("ABC123").used(false).build();
        when(resRepo.findByCode("ABC123")).thenReturn(Optional.of(fake));

        boolean result = service.checkIn("ABC123");

        assertTrue(result);
        verify(resRepo).save(fake);
    }

    @Test
    void testCheckInFailsIfAlreadyUsed() {
        Reservation fake = Reservation.builder().code("ABC123").used(true).build();
        when(resRepo.findByCode("ABC123")).thenReturn(Optional.of(fake));

        boolean result = service.checkIn("ABC123");

        assertFalse(result);
    }
}
