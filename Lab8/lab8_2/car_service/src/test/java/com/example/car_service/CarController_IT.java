package com.example.car_service;

import com.example.car_service.repository.CarRepository;

import com.example.car_service.repository.Car;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase

public class CarController_IT {

    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    void whenPostCar_thenCreateCar() {

        Car audi = new Car("audi", "a3sportback");

        ResponseEntity<Car> entity = restTemplate.postForEntity("/api/car", audi, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getMaker).containsOnly("audi");
    }

    @Test
    void whenGetCar_thenGetAllCars()  {
        Car audi = new Car("audi", "a3sportback");
        repository.saveAndFlush(audi);

        Car bmw = new Car("bmw", "m3");
        repository.saveAndFlush(bmw);

        Car mercedes = new Car("mercedes", "gla");
        repository.saveAndFlush(mercedes);

        ResponseEntity<List<Car>> response = restTemplate.exchange("/api/cars",HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>(){});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getMaker).containsExactly("audi", "bmw", "mercedes");

    }

}
