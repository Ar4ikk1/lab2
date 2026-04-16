package org.example.carpark.controller.validator;

public class RegExp {
    public static final String BRAND = "^.{2,30}$";
    public static final String MODEL = "^.{1,50}$";
    public static final String LICENSE_PLATE = "^[A-ZА-ЯІЇЄ]{2}\\d{4}[A-ZА-ЯІЇЄ]{2}$";
    public static final String COLOR = "^[A-Za-zА-Яа-яІЇЄіїє\\s]{3,20}$";
    public static final String NUMBER = "/\\d+";

}
