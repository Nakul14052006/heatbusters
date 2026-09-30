package org.example.heatbusters.config;

import org.example.heatbusters.entity.Ward;
import org.example.heatbusters.repository.WardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initWards(WardRepository repository) {
        return args -> {

            if (repository.count() > 0) {
                return;
            }

            repository.save(
                    Ward.builder()
                            .wardCode("W01")
                            .wardName("Central Zone")
                            .population(150000)
                            .elderlyPercentage(10)
                            .outdoorWorkerPercentage(10)
                            .populationDensity(12000)
                            .build()
            );

            repository.save(
                    Ward.builder()
                            .wardCode("W02")
                            .wardName("Industrial Zone")
                            .population(230000)
                            .elderlyPercentage(24)
                            .outdoorWorkerPercentage(32)
                            .populationDensity(28000)
                            .build()
            );

            repository.save(
                    Ward.builder()
                            .wardCode("W03")
                            .wardName("Residential Zone")
                            .population(190000)
                            .elderlyPercentage(20)
                            .outdoorWorkerPercentage(18)
                            .populationDensity(22000)
                            .build()
            );
        };
    }
}