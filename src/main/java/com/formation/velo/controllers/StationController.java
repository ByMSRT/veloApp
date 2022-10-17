package com.formation.velo.controllers;


import com.formation.velo.model.Stations;
import com.formation.velo.service.StationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class StationController {
	
	private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }


    @GetMapping("stations")
    public ResponseEntity<List<Stations>> getAll(){
		stationService.saveRecords();
        List<Stations> stations = stationService.findAll();

        return ResponseEntity.ok(stations);
    }

    @GetMapping("stations/{id}")
    public ResponseEntity<Optional<Stations>> getPersoneById(@PathVariable Integer id){
        Optional<Stations> stations = stationService.findById(id);

        return ResponseEntity.ok(stations);
    }

 /*    @PostMapping("stations/add")
    public ResponseEntity<Stations> add(@RequestParam String adress, @RequestParam int available_bikes, @RequestParam int available_bikes_stands, @RequestParam double lattitude, @RequestParam double longitude, @RequestParam String name, @RequestParam String record_id, @RequestParam String status){

        Stations stations = stationService.save(Stations.builder().address(adress).available_bikes(available_bikes).available_bike_stands(available_bikes_stands).lattitude(lattitude).longitude(longitude).name(name).recordid(record_id).status(status).build());
        return ResponseEntity.ok(stations);
    } */



 /*    @DeleteMapping("stations/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        stationService.deleteById(id);
        return ResponseEntity.ok("deleted");
    } */

/*     @PostMapping("stations/update")
    public ResponseEntity<String> update(@RequestBody Stations stations){
        stationService.save(stations);
        return ResponseEntity.ok("updated");
    } */


}
