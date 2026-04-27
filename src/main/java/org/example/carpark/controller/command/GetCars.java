package org.example.carpark.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.carpark.model.entity.Car;
import org.example.carpark.service.CarService;
import utils.AttributesHolder;
import utils.PagesHolder;

import java.util.List;

public class GetCars implements Command {
    private final CarService carService = CarService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String brand = request.getParameter(AttributesHolder.BRAND);

        List<Car> cars;

        if (brand != null && !brand.trim().isEmpty()) {
            cars = carService.getCarsByBrand(brand.trim());
        } else {
            cars = carService.getAllCars();
        }

        request.setAttribute(AttributesHolder.CARS, cars);

        return PagesHolder.CARS;
    }
}