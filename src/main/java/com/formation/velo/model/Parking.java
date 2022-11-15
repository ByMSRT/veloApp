package com.formation.velo.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "parking")
public class Parking implements Serializable {
    private static final long serialVersionUID = -767070904974486428L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Id est obligatoire")
    private String recordId;
    private String grpNom;
    private Integer grpStatut;
    private Integer grpExploitation;
    private Integer grpDisponible;
    private Double longitude;
    private Double latitude;
}

