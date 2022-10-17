package com.formation.velo.service;

import com.formation.velo.api.OpenData;
import com.formation.velo.model.Stations;

import java.util.List;
import java.util.Optional;

public interface StationService {

    List<Stations> findAll();
    Optional<Stations> findById(Integer id);
    Stations save(Stations station);

    void deleteById(Integer id);

    void delete(Stations station);

    void saveRecords();

    Optional<Stations> findByRecordId(String recordId);
}
