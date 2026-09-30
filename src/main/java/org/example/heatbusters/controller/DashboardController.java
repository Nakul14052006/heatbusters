package org.example.heatbusters.controller;

import org.example.heatbusters.dto.DashboardWard;
import org.example.heatbusters.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://localhost:5500")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService
    ) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public List<DashboardWard> dashboard() {

        return dashboardService.getDashboard(
                28.6139,
                77.2090
        );
    }
}