package com.example.car_service;

import java.util.List;
import java.util.Optional;

import com.example.car_service.repository.Car;
import com.example.car_service.repository.CarRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    void findByCarId_isvalid() {
        Car audi = new Car("audi", "a3sportback", "Hatchback", "Gasoline");
        entityManager.persistAndFlush(audi);
        Optional<Car> found = carRepository.findByCarId(audi.getcarId());
        assertThat(found).isPresent().contains(audi);
    }

    @Test
    void findByCarId_isinvalid() {
        Car fromDb = carRepository.findByCarId(-111L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    void findAll() {
        Car audi = new Car("audi", "a3sportback", "Hatchback", "Gasoline");
        Car bmw = new Car("bmw", "m3", "Sedan", "Gasoline");
        Car mercedes = new Car("mercedes", "gla", "SUV", "Diesel");

        entityManager.persist(audi);
        entityManager.persist(bmw);
        entityManager.persist(mercedes);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3)
                .extracting(Car::getMaker)
                .containsExactlyInAnyOrder("audi", "bmw", "mercedes");
    }
}
