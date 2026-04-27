package org.example.carpark.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.carpark.controller.FrontController;
import org.example.carpark.controller.validator.CarValidator;
import org.example.carpark.controller.validator.Errors;
import org.example.carpark.controller.validator.Validator;
import org.example.carpark.model.entity.Car;
import org.example.carpark.model.entity.CarPark;
import org.example.carpark.service.CarParkService;
import org.example.carpark.service.CarService;
import org.example.carpark.service.exception.ServiceException;
import utils.AttributesHolder;
import utils.PagesHolder;
import utils.PathsHolder;
import java.io.IOException;
import java.util.List;

public class AddCar implements Command {
    private final CarParkService carParkService = CarParkService.getInstance();
    private final CarService carService = CarService.getInstance();
    private final Validator<Car> carValidator = new CarValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Car car = buildCar(request);
        Errors errors = new Errors();

        if (carValidator.validate(car, errors)) {
            try {
                carService.create(car);
                response.sendRedirect(PathsHolder.CARS);
                return FrontController.REDIRECT;
            } catch (ServiceException e) {
                errors.addMessage(AttributesHolder.LICENSE_PLATE, e.getMessageKey());
                errors.setHasErrors(true);
            }
        }

        request.removeAttribute(AttributesHolder.ERROR_MESSAGE);
        request.setAttribute(AttributesHolder.ERRORS, errors);
        request.setAttribute(AttributesHolder.NEW_MODE, true);

        List<CarPark> carParks = carParkService.getAll();
        request.setAttribute(AttributesHolder.CAR_PARKS, carParks);
        request.setAttribute(AttributesHolder.CAR, car);

        return PagesHolder.CAR;
    }

    private Car buildCar(HttpServletRequest request) {
        String brand = request.getParameter(AttributesHolder.BRAND);
        String model = request.getParameter(AttributesHolder.MODEL);
        String color = request.getParameter(AttributesHolder.COLOR);
        String licensePlate = request.getParameter(AttributesHolder.LICENSE_PLATE);

        int year;
        try {
            year = Integer.parseInt(request.getParameter(AttributesHolder.YEAR));
        } catch (NumberFormatException e) {
            year = 0;
        }

        return new Car(brand, model, color, licensePlate, year, getCarPark(request));
    }

    private CarPark getCarPark(HttpServletRequest request) {
        CarPark carPark;
        try {
            int carParkId = Integer.parseInt(request.getParameter(AttributesHolder.CAR_PARK_ID));
            carPark = new CarPark();
            carPark.setId(carParkId);
        } catch (NumberFormatException e) {
            carPark = null;
        }
        return carPark;
    }
}
