package pracaDomowaZapisDoBazyDanych.model;

import lombok.*;


@Getter
@ToString
@Setter
public class Car {
    private Integer id;
    private final String brand;
    private final String model;
    private final int mileage;

    public Car(Integer id, String brand, String model, int mileage) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.mileage = mileage;
    }
}

