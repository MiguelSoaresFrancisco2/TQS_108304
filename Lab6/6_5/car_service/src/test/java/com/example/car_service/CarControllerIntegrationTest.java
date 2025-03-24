package com.example.car_service;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.example.car_service.repository.Car;
import com.example.car_service.repository.CarRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class CarControllerIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:14.5")
            .withDatabaseName("testdb")
            .withUsername("user")
            .withPassword("pass");

    @LocalServerPort
    private int port;

    @Autowired
    private CarRepository carRepository;

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "none"); 
        registry.add("spring.flyway.enabled", () -> "true");        
    }

    @BeforeEach
    void setUp() {
        RestAssured.port = port;

        // Limpa e insere dados de teste
        carRepository.deleteAll();
        carRepository.save(new Car("BMW", "Serie 5", "Sedan", "Gasoline"));
        carRepository.save(new Car("Tesla", "Model S", "Luxury", "Electric"));
    }

    @Test
    void testGetCarsFromApi() {
        when()
                .get("/api/cars")
                .then()
                .statusCode(200)
                .body("", hasSize(2))
                .body("[0].maker", equalTo("BMW"))
                .body("[1].model", equalTo("Model S"));
    }
}
