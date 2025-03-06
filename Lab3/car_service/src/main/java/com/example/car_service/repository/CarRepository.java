package com.example.car_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.car_service.repository.*;
import java.util.*;

@Repository
public interface CarRepository  extends JpaRepository<Car, Long>{

        public Optional<Car> findByCarId(Long carId);

        public List<Car> findAll();    

        public List<Car> findBySegmentAndMotorType(String segment, String motorType);

}
