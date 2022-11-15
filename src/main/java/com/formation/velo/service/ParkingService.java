package com.formation.velo.service;

import java.util.List;
import java.util.Optional;

import com.formation.velo.model.Parking;

public interface ParkingService {
    List<Parking> findAll();
    Optional<Parking> findById(Integer id);
    Parking save(Parking parking);

    void deleteById(Integer id);
    void delete(Parking parking);

    void getRecord();

    Optional<Parking> findByRecordId(String recordId);
}
