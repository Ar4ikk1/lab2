package org.example.carpark.service;

import org.example.carpark.model.dao.CarDao;
import org.example.carpark.model.dao.DaoConnection;
import org.example.carpark.model.dao.DaoFactory;
import org.example.carpark.model.entity.Car;
import org.example.carpark.service.exception.ServiceException;
import utils.ErrorMessages;

import java.util.List;
import java.util.Optional;

public class CarService {
    final DaoFactory daoFactory = DaoFactory.getInstance();

    static final CarService carService = new CarService();

    public static CarService getInstance() {
        return carService;
    }

    public void create(Car car){
        try(DaoConnection connection = daoFactory.getConnection()) {
            CarDao carDao = daoFactory.createCarDao(connection);
            connection.begin();
            Optional<Car> existingCar = carDao.findByLicensePlate(car.getLicensePlate());
            checkIfCarExists(existingCar);
            carDao.create(car);
            connection.commit();
        }
    }
    public void delete(int carId){
        try(DaoConnection connection = daoFactory.getConnection()) {
            CarDao carDao = daoFactory.createCarDao(connection);
            carDao.delete(carId);
        }
    }
    public void update(Car car){
        try(DaoConnection connection = daoFactory.getConnection()) {
            CarDao carDao = daoFactory.createCarDao(connection);
            connection.begin();
            Optional<Car> existingCar = carDao.findByLicensePlate(car.getLicensePlate());
            if(existingCar.isPresent()){
                if(existingCar.get().getId() != car.getId()){
                    checkIfCarExists(existingCar);
                }
            }
            carDao.update(car);
            connection.commit();
        }
    }
    public Optional<Car> getCarById(int carId){
        try(DaoConnection connection = daoFactory.getConnection()) {
            CarDao carDao = daoFactory.createCarDao(connection);
            Optional<Car> car = carDao.find(carId);
            return car;
        }
    }
    public List<Car> getAllCars(){
        try(DaoConnection connection = daoFactory.getConnection()) {
            CarDao carDao = daoFactory.createCarDao(connection);
            List<Car> cars = carDao.findAll();
            return cars;
        }
    }
    public List<Car> getCarsByBrand(String brand){
        try(DaoConnection connection = daoFactory.getConnection()) {
            CarDao carDao = daoFactory.createCarDao(connection);
            List<Car> cars = carDao.findByBrand(brand);
            return cars;
        }
    }

    private void checkIfCarExists(Optional<Car> car){
        if(car.isPresent()){
            throw new ServiceException(ErrorMessages.CAR_ALREADY_EXISTS).addLogMessage(ErrorMessages.CAR_ALREADY_EXISTS + " (" +  car.get().getLicensePlate() + ")");
        }
    }
}
