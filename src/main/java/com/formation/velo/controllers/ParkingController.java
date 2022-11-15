package com.formation.velo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formation.velo.model.Parking;
import com.formation.velo.service.ParkingService;

@Controller
@RequestMapping("/api")
public class ParkingController {
    private final ParkingService parkingService;
    
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("parking")
    public ResponseEntity<List<Parking>> findAll() {
        List<Parking> parkings = parkingService.findAll();

        return ResponseEntity.ok(parkings);
    }

    @GetMapping("parking/{id}")
    public ResponseEntity<Optional<Parking>> getParkingId(@PathVariable Integer id) {
        Optional<Parking> parking = parkingService.findById(id);

        return ResponseEntity.ok(parking);
    }

    @DeleteMapping("parking/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        parkingService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    @PostMapping("parking/update")
    public ResponseEntity<String> update(@RequestBody Parking parking) {
        parkingService.save(parking);
        return ResponseEntity.ok("updated");
    }

    @PostMapping("parking/add")
    public ResponseEntity<Parking> add(@RequestParam String recordId, @RequestParam String grpNom, @RequestParam int id, @RequestParam int grpStatut, @RequestParam int grpExploitation, @RequestParam int grpDisponible, @RequestParam double longitude, @RequestParam double latitude){

        Parking parking = parkingService.save(Parking.builder().recordId(recordId).grpNom(grpNom).id(id).grpStatut(grpStatut).grpExploitation(grpExploitation).grpDisponible(grpDisponible).longitude(longitude).latitude(latitude).build());
        return ResponseEntity.ok(parking);
    }
}
