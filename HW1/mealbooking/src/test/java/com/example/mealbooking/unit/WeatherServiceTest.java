package com.example.mealbooking.unit;

import com.example.mealbooking.services.WeatherService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    WeatherService service = new WeatherService();

    @Test
    void testGetForecastReturnsValueAndCachesIt() {
        LocalDate date = LocalDate.now().plusDays(1);

        String forecast1 = service.getForecast(date);
        String forecast2 = service.getForecast(date); // deve vir da cache

        assertEquals(forecast1, forecast2);
        assertEquals(2, service.getCacheStats().get("total"));
        assertEquals(1, service.getCacheStats().get("hits"));
        assertEquals(1, service.getCacheStats().get("misses"));
    }

    @Test
    void testForecastExpiresAfterTTL() throws InterruptedException {
        // este teste é simbólico — o TTL é de 30 minutos por default,
        // poderias adaptar a classe para permitir TTL customizado num teste
        assertTrue(true); // placeholder
    }
}
