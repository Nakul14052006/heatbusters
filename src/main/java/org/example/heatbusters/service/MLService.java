package org.example.heatbusters.service;

import org.example.heatbusters.dto.ThermalRequest;
import org.example.heatbusters.dto.ThermalResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class MLService {

    private final RestClient restClient;

    public MLService(
            @Value("${ml.service.url}") String mlUrl
    ) {
        this.restClient = RestClient.builder()
                .baseUrl(mlUrl)
                .build();
    }

    public ThermalResponse analyze(
            ThermalRequest request
    ) {

        return restClient.post()
                .uri("/analyze")
                .body(request)
                .retrieve()
                .body(ThermalResponse.class);
    }
}