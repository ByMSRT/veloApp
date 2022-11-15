package com.formation.velo.model;

import lombok.*;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name= "pumps")
public class Pump {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pumpId;
    private String ville;
    private String pop;
    private String reg_name;
    private String com_arm_code;
    private String dep_name;
    private String com_code;
    private String epci_name;
    private int dep_code;
    private String services_service;
    private String horaires_automate;
    private Date prixMaj;
    private int id;
    private int reg_code;
    private String adresse;
    private Double longitude;
    private Double latitude;
    private String epci_code;
    private String cp;
    private double prixValeur;
    private String recordId;
    private String com_name;
}
