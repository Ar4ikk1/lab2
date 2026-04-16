package org.example.carpark.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.carpark.model.entity.Car;
import org.example.carpark.model.entity.CarPark;
import org.example.carpark.service.CarParkService;
import org.example.carpark.service.CarService;
import utils.AttributesHolder;
import utils.PagesHolder;
import utils.PathsHolder;

import java.util.List;
import java.util.Optional;

public class GetCar implements Command {
    private final CarParkService carParkService = CarParkService.getInstance();
    private final CarService carService = CarService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<CarPark> carParkList = carParkService.getAll();
        request.setAttribute(AttributesHolder.CAR_PARKS, carParkList);

        String path = request.getRequestURI();

        if (path.contains(PathsHolder.EDIT_CAR)) {
            int carId = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
            Optional<Car> carOptional = carService.getCarById(carId);

            if (carOptional.isPresent()) {
                request.setAttribute(AttributesHolder.CAR, carOptional.get());
            }
        }
        return PagesHolder.CAR;
    }
}
