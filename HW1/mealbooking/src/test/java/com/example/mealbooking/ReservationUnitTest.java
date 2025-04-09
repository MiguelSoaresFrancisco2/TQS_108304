package com.example.mealbooking;

import com.example.mealbooking.model.Meal;
import com.example.mealbooking.model.Reservation;
import com.example.mealbooking.service.ReservationService;
import com.example.mealbooking.service.MealService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.*;

public class ReservationUnitTest {

    @Mock
    private MealService mealService;

    @InjectMocks
    private ReservationService reservationService;

    private Meal meal;
    private Reservation reservation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        meal = new Meal();
        meal.setId(1L);
        meal.setName("Spaghetti");
        meal.setPrice(8.50);
        meal.setReserved(false);

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setMeal(meal);
        reservation.setReservationCode("ABC123");
        reservation.setMealName(meal.getName());
        reservation.setDayOfWeek("Monday");
    }

    @Test
    public void testIsFacilityFull() {
        // Simulando que o restaurante está cheio
        meal.setReserved(true);

        boolean isFull = reservationService.isFacilityFull(meal);
        assertTrue(isFull, "O restaurante deveria estar cheio");
    }

    @Test
    public void testIsServiceAvailable() {
        // Simulando que o serviço está disponível
        boolean isAvailable = reservationService.isServiceAvailable("Monday");
        assertTrue(isAvailable, "O serviço deveria estar disponível na segunda-feira");
    }

    @Test
    public void testTicketAlreadyUsed() {
        // Simulando que o ticket foi utilizado
        reservation.setUsed(true);

        assertTrue(reservationService.isTicketUsed(reservation), "O ticket deveria estar marcado como usado");
    }

    @Test
    public void testCacheBehavior() {
        // Simulando que o cache está funcionando corretamente
        // Usando o cache ou um comportamento específico que você queira testar aqui.
        assertDoesNotThrow(() -> reservationService.getMealFromCache("Spaghetti"));
    }
}
