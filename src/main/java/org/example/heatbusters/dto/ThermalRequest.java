package org.example.heatbusters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThermalRequest {

    private double temperature;

    private double humidity;

    private double wind_speed;

    private double solar_radiation;

    private double elderly_percentage;

    private double outdoor_worker_percentage;

    private double population_density;

    private double previous_admissions;
}