package com.formation.velo.api.client.parking;

import retrofit2.Call;
import retrofit2.http.GET;

public interface OpenDataNantesParking {
    @GET("/api/records/1.0/search/?dataset=244400404_parkings-publics-nantes-disponibilites&q=&sort=-grp_exploitation&facet=grp_nom&facet=grp_statut")
    Call<OpenDataParking> getRecords();
}
