package com.example.car_service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.car_service.controller.CarController;
import com.example.car_service.repository.CarRepository;
import com.example.car_service.service.CarManagerService;

import com.example.car_service.repository.Car;
import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.internal.verification.VerificationModeFactory;

@ExtendWith(MockitoExtension.class)
public class CarManagerServiceTest {
    @Mock(lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carService;

    @BeforeEach
    public void setUp() {
        Car audi = new Car("audi", "a3sportback", "Hatchback", "Gasoline");
        audi.setcarId(123L);
    
        Car bmw = new Car("bmw", "m3", "Sedan", "Gasoline");
        Car mercedes = new Car("mercedes", "gla", "SUV", "Diesel");
    
        List<Car> allCars = Arrays.asList(audi, bmw, mercedes);
    
        Mockito.when(carRepository.findById(audi.getcarId())).thenReturn(Optional.of(audi));
        Mockito.when(carRepository.findById(bmw.getcarId())).thenReturn(Optional.of(bmw));
        Mockito.when(carRepository.findById(0L)).thenReturn(Optional.empty());
        Mockito.when(carRepository.findById(999L)).thenReturn(Optional.empty());
        Mockito.when(carRepository.findAll()).thenReturn(allCars);
    }
    

    @Test
    void savecar() {
        Car audi = new Car("audi", "a3sportback", "Hatchback", "Gasoline");
        Mockito.when(carRepository.save(audi)).thenReturn(audi);
        assertThat(carService.save(audi).getModel()).isEqualTo("a3sportback");
    }
    

    @Test
    void getAllcars() {
        List<Car> allCars = carService.getAllCars();
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findAll();
        assertThat(allCars.size()).isEqualTo(3);

        assertThat(allCars.get(0).getMaker()).isEqualTo("audi");
        assertThat(allCars.get(1).getMaker()).isEqualTo("bmw");
        assertThat(allCars.get(2).getMaker()).isEqualTo("mercedes");

    }

    @Test
    void validId() {
        Long id = 123L;
        Optional<Car> fromdb = carService.getCarById(id);
        assertThat(fromdb.get().getcarId()).isEqualTo(id);
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());

    }

    @Test
    void invalidId() {
        Long id = 999L;
        Optional<Car> fromdb = carService.getCarById(id);
        Mockito.verify(carRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        assertThat(fromdb).isEmpty(); 
    }
    


    @Test
    public void testFindReplacementCar() {
        Car originalCar = new Car("Toyota", "Corolla", "Sedan", "Gasoline");
        originalCar.setcarId(1L);
    
        Car replacementCar = new Car("Honda", "Civic", "Sedan", "Gasoline");
        replacementCar.setcarId(2L);
    
        Mockito.when(carRepository.findByCarId(1L)).thenReturn(Optional.of(originalCar));
        Mockito.when(carRepository.findBySegmentAndMotorType("Sedan", "Gasoline"))
                .thenReturn(List.of(replacementCar));
    
        Optional<Car> result = carService.findReplacementCar(1L);
    
        assertThat(result).isPresent();
        assertThat(result.get().getMaker()).isEqualTo("Honda");
        assertThat(result.get().getModel()).isEqualTo("Civic");
    }
    

}
