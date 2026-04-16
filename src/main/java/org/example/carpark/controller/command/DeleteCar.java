package org.example.carpark.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.carpark.controller.FrontController;
import org.example.carpark.service.CarService;
import utils.PathsHolder;

import java.io.IOException;
import org.apache.log4j.Logger;

public class DeleteCar implements Command {
    private final CarService carService = CarService.getInstance();

    private static final Logger logger = Logger.getLogger(DeleteCar.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getRequestURI();
        int carId = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));

        logger.info("Deleting car with ID: " + carId);

        carService.delete(carId);

        response.sendRedirect(PathsHolder.CARS);

        return FrontController.REDIRECT;
    }
}


