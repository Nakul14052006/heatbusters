package org.example.heatbusters.service;

import org.example.heatbusters.dto.*;
import org.example.heatbusters.entity.Ward;
import org.example.heatbusters.repository.WardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class DashboardService {

    private final WardRepository wardRepository;
    private final WeatherService weatherService;
    private final MLService mlService;

    public DashboardService(
            WardRepository wardRepository,
            WeatherService weatherService,
            MLService mlService
    ) {
        this.wardRepository = wardRepository;
        this.weatherService = weatherService;
        this.mlService = mlService;
    }

    public List<DashboardWard> getDashboard(
            double latitude,
            double longitude
    ) {

        List<WeatherData> forecast =
                weatherService.getForecast(
                        latitude,
                        longitude
                );

        if (forecast.isEmpty()) {
            throw new RuntimeException(
                    "No weather data received"
            );
        }

        WeatherData weather = forecast.stream()
                .limit(24)
                .max(Comparator.comparingDouble(WeatherData::getTemperature))
                .orElseThrow(() ->
                        new RuntimeException("No forecast data available"));

        List<DashboardWard> result =
                new ArrayList<>();

        for (Ward ward :
                wardRepository.findAll()) {

            ThermalRequest request =
                    new ThermalRequest();

            request.setTemperature(
                    weather.getTemperature()
            );

            request.setHumidity(
                    weather.getHumidity()
            );

            request.setWind_speed(
                    weather.getWindSpeed()
            );

            request.setSolar_radiation(
                    weather.getSolarRadiation()
            );

            request.setElderly_percentage(
                    ward.getElderlyPercentage()
            );

            request.setOutdoor_worker_percentage(
                    ward.getOutdoorWorkerPercentage()
            );

            request.setPopulation_density(
                    ward.getPopulationDensity()
            );

            // Synthetic baseline for prototype
            request.setPrevious_admissions(
                    100
            );

            ThermalResponse risk =
                    mlService.analyze(
                            request
                    );

            DashboardWard item =
                    new DashboardWard();

            item.setWardId(
                    ward.getWardCode()
            );

            item.setWardName(
                    ward.getWardName()
            );

            item.setTemperature(
                    risk.getTemperature()
            );

            item.setHumidity(
                    risk.getHumidity()
            );

            item.setWindSpeed(
                    risk.getWind_speed()
            );

            item.setSolarRadiation(
                    risk.getSolar_radiation()
            );

            item.setHeatIndex(
                    risk.getHeat_index()
            );

            item.setUtci(
                    risk.getUtci()
            );

            item.setWbgt(
                    risk.getWbgt()
            );

            item.setThermalStress(
                    risk.getThermal_stress()
            );

            item.setPredictedAdmissions(
                    risk.getPredicted_admissions()
            );

            item.setHospitalizationRisk(
                    risk.getHospitalization_risk()
            );

            item.setRiskLevel(
                    risk.getRisk_level()
            );

            item.setRecommendation(
                    risk.getRecommendation()
            );

            result.add(item);
        }

        return result;
    }
}