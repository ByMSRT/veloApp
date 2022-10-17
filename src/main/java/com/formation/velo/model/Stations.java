package com.formation.velo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity

@Table(name = "stations")
public class Stations implements Serializable {

	private static final long serialVersionUID = -767070904974486421L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double lattitude;
	private Double longitude;
	private String status;
	private Integer bikeStands;
	private Integer availableBikes;
	private Integer availableBikeStands;
	private String recordId;
	private String address;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Stations station = (Stations) o;
		return Objects.equals(id, station.id) && Objects.equals(name, station.name) && Objects.equals(lattitude, station.lattitude) && Objects.equals(longitude, station.longitude) && Objects.equals(status, station.status) && Objects.equals(bikeStands, station.bikeStands) && Objects.equals(availableBikes, station.availableBikes) && Objects.equals(availableBikeStands, station.availableBikeStands) && Objects.equals(recordId, station.recordId) && Objects.equals(address, station.address);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, lattitude, longitude, status, bikeStands, availableBikes, availableBikeStands, recordId, address);
	}
}
