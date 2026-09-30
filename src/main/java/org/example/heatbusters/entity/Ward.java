package org.example.heatbusters.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String wardCode;

    private String wardName;

    private double population;

    private double elderlyPercentage;

    private double outdoorWorkerPercentage;

    private double populationDensity;
}