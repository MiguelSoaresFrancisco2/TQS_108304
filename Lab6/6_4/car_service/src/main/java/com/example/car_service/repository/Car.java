package com.example.car_service.repository;

import java.util.Objects;
import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long carId;
    private String maker;
    private String model;
    private String segment;
    private String motorType;

    

    public Car(){}
    
    public Car(String maker, String model, String segment, String motorType) {
        this.maker = maker;
        this.model = model;
        this.segment = segment;
        this.motorType = motorType;
    }

    public Long getcarId() {
        return carId;
    }

    public void setcarId(Long carId) {
        this.carId = carId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getMotorType() {
        return motorType;
    }

    public void setMotorType(String motorType) {
        this.motorType = motorType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Car car = (Car) o;
        return carId.equals(car.carId) && Objects.equals(maker, car.maker) && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, maker, model);
    }

}
