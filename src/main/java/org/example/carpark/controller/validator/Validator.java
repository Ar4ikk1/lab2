package org.example.carpark.controller.validator;

public interface Validator<E> {
    boolean validate(E e, Errors errors);
}
