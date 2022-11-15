package com.formation.velo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.velo.model.Pump;

@Repository
public interface PumpRepository extends JpaRepository<Pump, Integer>{
    Optional<Pump> findByRecordId(String recordId);
}
