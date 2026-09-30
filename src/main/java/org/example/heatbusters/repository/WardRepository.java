package org.example.heatbusters.repository;

import org.example.heatbusters.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WardRepository extends JpaRepository<Ward, Long> {

    Optional<Ward> findByWardCode(String wardCode);
}