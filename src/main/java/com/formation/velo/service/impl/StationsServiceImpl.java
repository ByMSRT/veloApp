package com.formation.velo.service.impl;

import com.formation.velo.api.OpenData;
import com.formation.velo.api.client.OpenDataNantesClient;
import com.formation.velo.model.Stations;
import com.formation.velo.service.StationService;

import lombok.extern.java.Log;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.formation.velo.repository.StationsRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
@Log
public class StationsServiceImpl implements StationService {
    
    public final StationsRepository stationsRepository;

    public StationsServiceImpl(StationsRepository repository) {
        this.stationsRepository = repository;
    }
    
    @Override
    public List<Stations> findAll() {
        return stationsRepository.findAll();
    }

    @Override
    public Optional<Stations> findById(Integer id) {
        return stationsRepository.findById(id);
    }

    @Override
    public Stations save(Stations stations) {
        return stationsRepository.save(stations);
    }

    @Override
    public void deleteById(Integer id) {
        stationsRepository.deleteById(id);
    }

    @Override
    public void delete(Stations stations) {
        stationsRepository.delete(stations);
    }

    @Override
    public void saveRecords() {
        String baseUrl = "https://data.nantesmetropole.fr/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        
        OpenDataNantesClient client = retrofit.create(OpenDataNantesClient.class);
        Call<OpenData> openDataNantesCall = client.getRecords();
        try {
            OpenData openDataNantes = openDataNantesCall.execute().body();
            
            Arrays.stream(openDataNantes.getRecords()).forEach(record -> {
                Optional<Stations> stationToUpdate = findByRecordId(record.getRecordId());

                if(stationToUpdate.isPresent()){
                    stationToUpdate.get().setAvailableBikes(record.getField().getAvailableBikes());
                    stationToUpdate.get().setAvailableBikeStands(record.getField().getAvailableBikeStands());
                    stationToUpdate.get().setBikeStands(record.getField().getBikeStands());
                    stationToUpdate.get().setLongitude(record.getField().getPosition()[1]);
                    stationToUpdate.get().setLattitude(record.getField().getPosition()[0]);
                    stationToUpdate.get().setStatus(record.getField().getStatus());
                    //save station
                    save(stationToUpdate.get());
                } else {
                    //create station
                    Stations newStation = Stations.builder()
                            .recordId(record.getRecordId())
                            .name(record.getField().getName())
                            .address(record.getField().getAddress())
                            .availableBikes(record.getField().getAvailableBikes())
                            .availableBikeStands(record.getField().getAvailableBikeStands())
                            .bikeStands(record.getField().getBikeStands())
                            .longitude(record.getField().getPosition()[1])
                            .lattitude(record.getField().getPosition()[0])
                            .status(record.getField().getStatus())
                            .build();
                    //save station
                    save(newStation);
                }
                
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
    }

    @Override
    public Optional<Stations> findByRecordId(String recordId) {
        return stationsRepository.findByRecordId(recordId);   
    }
}