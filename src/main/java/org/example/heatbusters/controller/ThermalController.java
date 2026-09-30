package org.example.heatbusters.controller;

import org.example.heatbusters.dto.ThermalRequest;
import org.example.heatbusters.dto.ThermalResponse;
import org.example.heatbusters.service.MLService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/thermal")
@CrossOrigin(origins = "http://localhost:5500")
public class ThermalController {

    private final MLService mlService;

    public ThermalController(
            MLService mlService
    ) {
        this.mlService = mlService;
    }

    @PostMapping("/analyze")
    public ThermalResponse analyze(
            @RequestBody ThermalRequest request
    ) {

        return mlService.analyze(
                request
        );
    }
}