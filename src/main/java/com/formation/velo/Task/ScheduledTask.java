package com.formation.velo.Task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.formation.velo.service.ParkingService;
import com.formation.velo.service.StationService;

import lombok.extern.java.Log;

@Component
@Log

public class ScheduledTask {

    private final StationService stationService;
    private final ParkingService parkingService;

    public ScheduledTask(StationService stationService, ParkingService parkingService) {
        this.stationService = stationService;
        this.parkingService = parkingService;
    }

    @Scheduled(fixedRate = 60000)
    public void searchNextMatchByCompetition(){
        log.info("➡️ Scheduled task started");
        stationService.saveRecords();
        parkingService.getRecord();
    }
}