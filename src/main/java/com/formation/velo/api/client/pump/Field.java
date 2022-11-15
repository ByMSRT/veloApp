package com.formation.velo.api.client.pump;

import java.util.Date;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Field {
        private String ville;
        private String pop;
        @SerializedName("reg_name")
        private String regName;
        @SerializedName("com_arm_code")
        private String comArmCode;
        @SerializedName("dep_name")
        private String depName;
        @SerializedName("prix_nom")
        private String prixNom;
        @SerializedName("com_code")
        private String comCode;
        @SerializedName("epci_name")
        private String epciName;
        @SerializedName("dep_code")
        private int depCode;
        @SerializedName("prix_id")
        private String prixId;
        @SerializedName("services_service")
        private String servicesService;
        @SerializedName("horaires_automate_24_24")
        private String horairesAutomate24_24;
        @SerializedName("com_arm_name")
        private String comArmName;
        @SerializedName("prix_maj")
        private Date prixMaj;
        private int id;
        @SerializedName("reg_code")
        private int regCode;
        private String adresse;
        private double[] geom;
        @SerializedName("epci_code")
        private String epciCode;
        private String cp;
        @SerializedName("prix_valeur")
        private double prixValeur;
        @SerializedName("com_name")
        private String comName;
}

