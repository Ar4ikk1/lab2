package org.example.carpark.model.dao;

import org.example.carpark.model.entity.CarPark;

import java.util.List;

public interface CarParkDao extends  GenericDao<CarPark> {
    List<CarPark> findByName(String name);
}
