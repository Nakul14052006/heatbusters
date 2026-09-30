package org.example.heatbusters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {

    private String time;

    private double temperature;

    private double humidity;

    private double windSpeed;

    private double solarRadiation;
}