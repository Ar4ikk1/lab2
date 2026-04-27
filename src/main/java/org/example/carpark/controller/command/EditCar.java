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

public class EditCar implements Command{
    private final CarParkService carParkService = CarParkService.getInstance();
    private final CarService carService = CarService.getInstance();
    private final Validator<Car> carValidator = new CarValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Car car = buildCar(request);
        Errors errors = new Errors();

        if (carValidator.validate(car, errors)) {
            try {
                carService.update(car);
                response.sendRedirect(PathsHolder.CARS);
                return FrontController.REDIRECT;
            } catch (ServiceException e) {
                errors.addMessage(AttributesHolder.LICENSE_PLATE, e.getMessageKey());
                errors.setHasErrors(true);
            }
        }

        request.removeAttribute(AttributesHolder.ERROR_MESSAGE);
        request.setAttribute(AttributesHolder.ERRORS, errors);

        List<CarPark> carParks = carParkService.getAll();
        request.setAttribute(AttributesHolder.CAR_PARKS, carParks);
        request.setAttribute(AttributesHolder.CAR, car);

        return PagesHolder.CAR;
    }

    private Car buildCar(HttpServletRequest request) {
        Car car = new Car(
                request.getParameter(AttributesHolder.BRAND),
                request.getParameter(AttributesHolder.MODEL),
                request.getParameter(AttributesHolder.COLOR),
                request.getParameter(AttributesHolder.LICENSE_PLATE),
                Integer.parseInt(request.getParameter(AttributesHolder.YEAR)),
                getCarPark(request)
        );
        car.setId(Integer.parseInt(request.getParameter(AttributesHolder.ID)));

        return car;
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
