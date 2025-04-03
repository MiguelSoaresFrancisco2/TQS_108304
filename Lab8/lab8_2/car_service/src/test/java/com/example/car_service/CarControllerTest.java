package com.example.car_service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;
import java.util.List;
import com.example.car_service.controller.CarController;
import com.example.car_service.repository.Car;
import com.example.car_service.service.CarManagerService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    private MockMvc mvc;

    @Autowired
    private CarController carController;

    @MockBean
    private CarManagerService car_service;

    @Test
    void whenPostCar_thenCreateCar() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(carController).build();

        Car audi = new Car("audi", "a3sportback", "Hatchback", "Gasoline");


        when(car_service.save(Mockito.any())).thenReturn(audi);

        mvc.perform(
        post("/api/car")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"maker\":\"audi\",\"model\":\"a3sportback\",\"segment\":\"Hatchback\",\"motorType\":\"Gasoline\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.model", is("a3sportback")));


        verify(car_service, times(1)).save(Mockito.any());
    }

    @Test
    void whenGetCar_thenGetAllCars() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(carController).build();

        Car audi = new Car("audi", "a3sportback", "Hatchback", "Gasoline");
        Car bmw = new Car("bmw", "m3", "Sedan", "Gasoline");
        Car mercedes = new Car("mercedes", "gla", "SUV", "Diesel");


        List<Car> allCars = Arrays.asList(audi, bmw, mercedes);
        when(car_service.getAllCars()).thenReturn(allCars);     

        mvc.perform(
                get("/api/cars")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].maker", is(audi.getMaker())))
                .andExpect(jsonPath("$[1].maker", is(bmw.getMaker())))
                .andExpect(jsonPath("$[2].maker", is(mercedes.getMaker())));

        verify(car_service, times(1)).getAllCars();
    }
}