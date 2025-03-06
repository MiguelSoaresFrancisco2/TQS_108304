package com.example.car_service.controller;

import com.example.car_service.repository.Car;
import com.example.car_service.service.CarManagerService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CarController {
    private final CarManagerService carManagerService;

    public CarController(CarManagerService carManagerService) {
        this.carManagerService = carManagerService;
    }

    @PostMapping("/car")
    public ResponseEntity<Car> createCar(@RequestBody Car oneCar) {
        HttpStatus status = HttpStatus.CREATED;
        Car saved = carManagerService.save(oneCar);
        return new ResponseEntity<>(saved, status);
    }

    @GetMapping("/cars")
    public List<Car> getAllCars() {
        return carManagerService.getAllCars();
    }

    @GetMapping("/car/{id}")
    public Optional<Car> getCarDetails(@PathVariable("id") Long carId) {
        return carManagerService.getCarById(carId);
    }

    @GetMapping("/car/replacement/{id}")
    public ResponseEntity<Car> getReplacementCar(@PathVariable("id") Long carId) {
        Optional<Car> replacementCar = carManagerService.findReplacementCar(carId);
        return replacementCar.map(car -> new ResponseEntity<>(car, HttpStatus.OK))
                             .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    

}
