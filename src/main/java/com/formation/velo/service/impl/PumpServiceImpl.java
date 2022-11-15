package com.formation.velo.service.impl;

import com.formation.velo.api.client.pump.GouvDataNantes;
import com.formation.velo.api.client.pump.GouvDataNantesClient;
import com.formation.velo.model.Pump;
import com.formation.velo.repository.PumpRepository;
import com.formation.velo.service.PumpService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PumpServiceImpl implements PumpService {

    private final PumpRepository pumpRepository;

    public PumpServiceImpl(PumpRepository repository) {
        this.pumpRepository = repository;
    }

    @Override
    public List<Pump> findAll() {
        return pumpRepository.findAll();
    }

    @Override
    public Optional<Pump> findById(Integer id) {
        return pumpRepository.findById(id);
    }

    @Override
    public Pump save(Pump pump) {
        return pumpRepository.save(pump);
    }

    @Override
    public void deleteById(Integer id) {
        pumpRepository.deleteById(id);
    }

    @Override
    public void delete(Pump pump) {
        pumpRepository.delete(pump);
    }

    @Override
    public void getRecords(){
        String baseURL = "https://data.economie.gouv.fr";
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GouvDataNantesClient client = retrofit.create(GouvDataNantesClient.class);
        Call<GouvDataNantes> gouvDataNantesCall = client.getRecords();

        try {
            GouvDataNantes gouvDataNantes = gouvDataNantesCall.execute().body();

            Arrays.stream(gouvDataNantes.getRecords()).forEach(record -> {
                Optional<Pump> pump = findByRecordId(record.getRecordId());
                if (pump.isPresent()) {
                    pump.get()
                            .setHoraires_automate(record.getField().getHorairesAutomate24_24());
                    pump.get().setPrixMaj(record.getField().getPrixMaj());

                    pump.get().setPrixValeur(record.getField().getPrixValeur());

                    save(pump.get());
                } else {
                    Pump newPump = Pump.builder()
                            .ville(record.getField().getVille())
                            .pop(record.getField().getPop())
                            .reg_name(record.getField().getRegName())
                            .com_arm_code(record.getField().getComArmCode())
                            .latitude(record.getField().getGeom()[0])
                            .longitude(record.getField().getGeom()[1])
                            .dep_name(record.getField().getDepName())
                            .com_code(record.getField().getComCode())
                            .epci_name(record.getField().getEpciName())
                            .dep_code(record.getField().getDepCode())
                            .services_service(record.getField().getServicesService())
                            .horaires_automate(record.getField().getHorairesAutomate24_24())
                            .prixMaj(record.getField().getPrixMaj())
                            .id(record.getField().getId())
                            .reg_code(record.getField().getRegCode())
                            .adresse(record.getField().getAdresse())
                            .epci_code(record.getField().getEpciCode())
                            .cp(record.getField().getCp())
                            .prixValeur(record.getField().getPrixValeur())
                            .recordId(record.getRecordId())
                            .build();

                    save(newPump);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Pump> findByRecordId(String recordId) {
        return pumpRepository.findByRecordId(recordId);
    }
}



