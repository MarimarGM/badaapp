package com.badapp.badapp.dto.weather;

import java.util.List;

public class TemperatureResponseDto {

    private String dt;
    private TemperatureDescriptionResponseDto temp;
    private double preasure;
    private int humidity;

    private List<WeatherDescriptionResponseDto> weather;

    private double speed;
    private int deg;
    private int clouds;
}
