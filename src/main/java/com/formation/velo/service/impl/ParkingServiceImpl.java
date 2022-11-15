package com.formation.velo.service.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.formation.velo.api.client.parking.OpenDataNantesParking;
import com.formation.velo.api.client.parking.OpenDataParking;
import com.formation.velo.model.Parking;
import com.formation.velo.repository.ParkingRepository;
import com.formation.velo.service.ParkingService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.java.Log;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
@Log
public class ParkingServiceImpl implements ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingServiceImpl(ParkingRepository repo) {
        this.parkingRepository = repo;
    }

    @Override
    public List<Parking> findAll() {
        
        return parkingRepository.findAll();
    }

    @Override
    public Optional<Parking> findById(Integer id) {
        
        return parkingRepository.findById(id);
    }

    @Override
    public Parking save(Parking parking) {
        
        return parkingRepository.save(parking);
    }

    @Override
    public void deleteById(Integer id) {
        parkingRepository.deleteById(id);
        
    }

    @Override
    public void delete(Parking parking) {
        parkingRepository.delete(parking);
        
    }

    @Override
    public void getRecord() {
        String baseUrl = "https://data.nantesmetropole.fr/";
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        OpenDataNantesParking client = retrofit.create(OpenDataNantesParking.class);
        Call<OpenDataParking> openDataParkingCall = client.getRecords();
        
        try {
            OpenDataParking openDataParking = openDataParkingCall.execute().body();

            Arrays.stream(openDataParking.getRecords()).forEach(record -> {
                Optional<Parking> parking = findByRecordId(record.getRecordId());

               if(parking.isPresent()){
                    parking.get().setGrpDisponible(record.getField().getGrpDisponible());
                    parking.get().setGrpExploitation(record.getField().getGrpExploitation());
                    parking.get().setGrpNom(record.getField().getGrpNom());
                    parking.get().setGrpStatut(record.getField().getGrpStatut());
                    
                    save(parking.get());
               } else {
                // verify if getLocalisation() is not null
                if(record.getField().getLocation() != null){
                    Parking newParking = Parking.builder()
                    .recordId(record.getRecordId())
                    .grpNom(record.getField().getGrpNom())
                    .grpDisponible(record.getField().getGrpDisponible())
                    .grpExploitation(record.getField().getGrpExploitation())
                    .grpStatut(record.getField().getGrpStatut())
                    .longitude(record.getField().getLocation()[1])
                    .latitude(record.getField().getLocation()[0])
                    .build();
                save(newParking);
                } else {
                    Parking newParking = Parking.builder()
                    .recordId(record.getRecordId())
                    .grpNom(record.getField().getGrpNom())
                    .grpDisponible(record.getField().getGrpDisponible())
                    .grpExploitation(record.getField().getGrpExploitation())
                    .grpStatut(record.getField().getGrpStatut())
                    .build();
                save(newParking);
                }
                    
               }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Parking> findByRecordId(String recordId) {
        
        return parkingRepository.findByRecordId(recordId);
    }
    
}
