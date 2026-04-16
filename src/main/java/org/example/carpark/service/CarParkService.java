package org.example.carpark.service;

import org.example.carpark.model.dao.CarParkDao;
import org.example.carpark.model.dao.DaoConnection;
import org.example.carpark.model.dao.DaoFactory;
import org.example.carpark.model.entity.CarPark;

import java.util.List;

public class CarParkService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private static final CarParkService carParkService = new CarParkService();

    public static CarParkService getInstance() {
        return carParkService;
    }

    public List<CarPark> getAll() {
        try( DaoConnection connection = daoFactory.getConnection() ){
            CarParkDao carParkDao = daoFactory.createCarParkDao(connection);
            List <CarPark> carParks = carParkDao.findAll();
            return carParks;
        }
    }
}
