package org.example.heatbusters.dto;

import lombok.Data;

@Data
public class DashboardWard {

    private String wardId;
    private String wardName;

    private double temperature;
    private double humidity;
    private double windSpeed;
    private double solarRadiation;

    private double heatIndex;
    private double utci;
    private double wbgt;

    private double thermalStress;

    private double predictedAdmissions;

    private double hospitalizationRisk;

    private String riskLevel;

    private String recommendation;
}