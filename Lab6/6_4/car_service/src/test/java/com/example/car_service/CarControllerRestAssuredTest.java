package com.example.car_service;

import com.example.car_service.controller.CarController;
import com.example.car_service.repository.Car;
import com.example.car_service.service.CarManagerService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(CarController.class)
public class CarControllerRestAssuredTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService car_service;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void whenGetCars_thenReturnsListOfCars() {
        Car audi = new Car("audi", "a3sportback", "Hatchback", "Gasoline");
        Car bmw = new Car("bmw", "m3", "Sedan", "Gasoline");
        Car mercedes = new Car("mercedes", "gla", "SUV", "Diesel");

        List<Car> allCars = Arrays.asList(audi, bmw, mercedes);
        when(car_service.getAllCars()).thenReturn(allCars);

        when()
            .get("/api/cars")
        .then()
            .statusCode(200)
            .body("", hasSize(3))
            .body("[0].maker", equalTo("audi"))
            .body("[1].maker", equalTo("bmw"))
            .body("[2].maker", equalTo("mercedes"));

        verify(car_service, times(1)).getAllCars();
    }

    @Test
    void whenPostCar_thenCreatesCar() {
        Car audi = new Car("audi", "a3sportback", "Hatchback", "Gasoline");
        when(car_service.save(Mockito.any())).thenReturn(audi);

        given()
            .contentType("application/json")
            .body("{\"maker\":\"audi\",\"model\":\"a3sportback\",\"segment\":\"Hatchback\",\"motorType\":\"Gasoline\"}")
        .when()
            .post("/api/car")
        .then()
            .statusCode(201)
            .body("model", equalTo("a3sportback"));

        verify(car_service, times(1)).save(Mockito.any());
    }
}
