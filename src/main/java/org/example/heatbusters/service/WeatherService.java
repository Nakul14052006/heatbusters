package org.example.heatbusters.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.heatbusters.dto.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {

    private final RestClient restClient;
    private final ObjectMapper objectMapper;

    public WeatherService() {

        this.restClient = RestClient.builder()
                .baseUrl("https://api.open-meteo.com")
                .build();

        this.objectMapper = new ObjectMapper();
    }

    public List<WeatherData> getForecast(
            double latitude,
            double longitude
    ) {

        String response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/forecast")
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam(
                                "hourly",
                                "temperature_2m,relative_humidity_2m,wind_speed_10m,shortwave_radiation"
                        )
                        .queryParam("forecast_days", 5)
                        .queryParam("timezone", "auto")
                        .build())
                .retrieve()
                .body(String.class);

        try {

            JsonNode root = objectMapper.readTree(response);

            JsonNode hourly = root.get("hourly");

            JsonNode times = hourly.get("time");
            JsonNode temperatures = hourly.get("temperature_2m");
            JsonNode humidity = hourly.get("relative_humidity_2m");
            JsonNode wind = hourly.get("wind_speed_10m");
            JsonNode radiation = hourly.get("shortwave_radiation");

            List<WeatherData> result = new ArrayList<>();

            for (int i = 0; i < times.size(); i++) {

                WeatherData data = new WeatherData();

                data.setTime(times.get(i).asText());
                data.setTemperature(temperatures.get(i).asDouble());
                data.setHumidity(humidity.get(i).asDouble());

                // Open-Meteo default wind unit is km/h.
                // Convert to m/s.
                data.setWindSpeed(
                        wind.get(i).asDouble() / 3.6
                );

                data.setSolarRadiation(
                        radiation.get(i).asDouble()
                );

                result.add(data);
            }

            return result;

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to parse weather API response",
                    e
            );
        }
    }
}