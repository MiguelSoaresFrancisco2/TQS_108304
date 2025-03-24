package com.example.car_service.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


import com.example.car_service.repository.CarRepository;
import com.example.car_service.repository.Car;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CarManagerService {

    @Autowired
    private final CarRepository carRepository;

    public CarManagerService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public Car save(Car car) {
        return carRepository.save(car);
    };

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> getCarById(Long carId) {
        return carRepository.findById(carId);
    }

    public Optional<Car> findReplacementCar(Long carId) {
        Optional<Car> givenCar = carRepository.findByCarId(carId);
        if (givenCar.isEmpty()) {
            return Optional.empty();
        }
        
        List<Car> potentialReplacements = carRepository.findBySegmentAndMotorType(
            givenCar.get().getSegment(), 
            givenCar.get().getMotorType()
        );

        return potentialReplacements.stream()
                .filter(car -> !car.getcarId().equals(carId)) 
                .findFirst();
    }
    
}
