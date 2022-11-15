package com.formation.velo.service;

import java.util.List;
import java.util.Optional;

import com.formation.velo.model.Pump;

public interface PumpService {
    List<Pump> findAll();
    Optional<Pump> findById(Integer id);
    Pump save(Pump pump);

    void deleteById(Integer id);

    void delete(Pump pump);

    void getRecords();

    Optional<Pump> findByRecordId(String recordId);
}
