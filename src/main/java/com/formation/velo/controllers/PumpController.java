package com.formation.velo.controllers;



import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.formation.velo.model.Pump;
import com.formation.velo.service.PumpService;

@Controller
@RequestMapping("/api")
public class PumpController {
    private final PumpService pumpService;

    public PumpController(PumpService pumpService) {
        this.pumpService = pumpService;
    }

    @GetMapping("pumps")
    public ResponseEntity<List<Pump>> getAll(){
        pumpService.getRecords();
        List<Pump> pumps = pumpService.findAll();

        return ResponseEntity.ok(pumps);
    }

    @GetMapping("pumps/{id}")
    public ResponseEntity<Optional<Pump>> getStationById(@PathVariable Integer id){
        Optional<Pump> pump = pumpService.findById(id);

        return ResponseEntity.ok(pump);
    }

    @PostMapping("pumps/add")
    public ResponseEntity<Pump> add(@RequestParam String ville, @RequestParam String pop, @RequestParam String regName, @RequestParam String comArmCode, @RequestParam String depName, @RequestParam String comCode, @RequestParam String epciName, @RequestParam int depCode, @RequestParam String servicesService, @RequestParam String horaireAutomate, @RequestParam Date prixMaj, @RequestParam int id, @RequestParam int regCode, @RequestParam String adresse, @RequestParam double longitude, @RequestParam double latitude, @RequestParam String epciCode, @RequestParam String cp, @RequestParam double prixValeur, @RequestParam String recordId){

        Pump pump = pumpService.save(Pump.builder().ville(ville).pop(pop).reg_name(regName).com_arm_code(comArmCode).dep_name(depName).com_code(comCode).epci_name(epciName).dep_code(depCode).services_service(servicesService).horaires_automate(horaireAutomate).prixMaj(prixMaj).id(id).reg_code(regCode).adresse(adresse).longitude(longitude).latitude(latitude).epci_code(epciCode).cp(cp).prixValeur(prixValeur).recordId(recordId).build());
        return ResponseEntity.ok(pump);
    }

    @DeleteMapping("pumps/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        pumpService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    @PostMapping("pumps/update")
    public ResponseEntity<String> update(@RequestBody Pump pump){
        pumpService.save(pump);
        return ResponseEntity.ok("updated");
    }
}
