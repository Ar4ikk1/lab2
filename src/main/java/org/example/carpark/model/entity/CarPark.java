package org.example.carpark.model.entity;

import java.util.Objects;

public class CarPark {
    private int id;
    private String name;

    public CarPark() {

    }
    public CarPark(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CarPark carPark = (CarPark) o;
        return id == carPark.id && Objects.equals(name, carPark.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CarPark{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
