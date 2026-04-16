package org.example.carpark.model.dao;

import org.example.carpark.model.entity.Car;
import java.util.List;
import java.util.Optional;

public interface CarDao extends GenericDao<Car> {
    List<Car> findByBrand(String brand);
    Optional<Car> findByLicensePlate(String licensePlate);
}
