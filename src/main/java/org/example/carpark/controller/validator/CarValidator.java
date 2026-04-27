package org.example.carpark.controller.validator;

import org.example.carpark.model.entity.Car;
import utils.AttributesHolder;
import utils.ErrorMessages;

import java.time.Year;
import java.util.regex.Pattern;

public class CarValidator implements Validator<Car> {
    private final Pattern brandPattern = Pattern.compile(RegExp.BRAND);
    private final Pattern modelPattern = Pattern.compile(RegExp.MODEL);
    private final Pattern colorPattern = Pattern.compile(RegExp.COLOR);
    private final Pattern licensePlatePattern = Pattern.compile(RegExp.LICENSE_PLATE);

    @Override
    public boolean validate(Car car, Errors errors) {
        if(car == null){
            reject(errors, AttributesHolder.CAR, ErrorMessages.INVALID);
            return false;
        }
        if(!brandPattern.matcher(car.getBrand()).matches()){
            reject(errors, AttributesHolder.BRAND, ErrorMessages.BRAND_INVALID);
        }
        if(!modelPattern.matcher(car.getModel()).matches()){
            reject(errors, AttributesHolder.MODEL, ErrorMessages.MODEL_INVALID);
        }
        if(!colorPattern.matcher(car.getColor()).matches()){
            reject(errors, AttributesHolder.COLOR, ErrorMessages.COLOR_INVALID);
        }
        if(!licensePlatePattern.matcher(car.getLicensePlate()).matches()){
            reject(errors, AttributesHolder.LICENSE_PLATE, ErrorMessages.LICENSE_PLATE_INVALID);
        }
        int currentYear = Year.now().getValue();
        if (car.getYear() < 1900 || car.getYear() > currentYear) {
            reject(errors, AttributesHolder.YEAR, ErrorMessages.YEAR_INVALID);
        }
        if (car.getCarPark() == null || car.getCarPark().getId() == 0) {
            reject(errors, AttributesHolder.CAR_PARK_ID, ErrorMessages.CAR_PARK_SELECT_INVALID);
        }
        return !errors.hasError();
    }
    private void reject(Errors errors, String attribute, String message){
        errors.addMessage(attribute, message);
        errors.setHasErrors(true);
    }
}
