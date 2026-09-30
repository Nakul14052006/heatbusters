package org.example.heatbusters.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThermalResponse {

    private double temperature;
    private double humidity;
    private double wind_speed;
    private double solar_radiation;

    private double heat_index;
    private double utci;
    private double wbgt;

    private double thermal_stress;

    private double predicted_admissions;

    private double hospitalization_risk;

    private String risk_level;

    private String recommendation;
}