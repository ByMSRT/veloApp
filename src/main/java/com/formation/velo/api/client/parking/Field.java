package com.formation.velo.api.client.parking;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Field {
    @SerializedName("grp_nom")
    private String grpNom;
    @SerializedName("grp_statut")
    private int grpStatut;
    @SerializedName("grp_exploitation")
    private int grpExploitation;
    @SerializedName("grp_disponible")
    private int grpDisponible;
    private double[] location;
}
