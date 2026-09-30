package org.example.heatbusters.controller;




import org.example.heatbusters.dto.WeatherData;
import org.example.heatbusters.service.WeatherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "http://localhost:5500")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public List<WeatherData> getWeather(
            @RequestParam double latitude,
            @RequestParam double longitude
    ) {

        return weatherService.getForecast(
                latitude,
                longitude
        );
    }
}