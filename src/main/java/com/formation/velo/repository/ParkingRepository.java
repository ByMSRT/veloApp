package com.formation.velo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.velo.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Integer> {
    Optional<Parking> findByRecordId(String recordId);
}
