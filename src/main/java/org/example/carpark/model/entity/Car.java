package org.example.carpark.model.entity;

import java.util.Objects;

public class Car {
    private int id;
    private String brand;
    private String model;
    private String color;
    private String licensePlate;
    private int year;
    private CarPark carPark;

    public Car(String brand, String model, String color, String licensePlate, int year, CarPark carPark) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.licensePlate = licensePlate;
        this.year = year;
        this.carPark = carPark;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCarPark(CarPark carPark) {
        this.carPark = carPark;
    }

    public CarPark getCarPark() {
        return carPark;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && Objects.equals(brand, car.brand) && Objects.equals(model, car.model) && Objects.equals(color, car.color) && Objects.equals(licensePlate, car.licensePlate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, color, licensePlate, year);
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", year=" + year +
                '}';
    }
}
