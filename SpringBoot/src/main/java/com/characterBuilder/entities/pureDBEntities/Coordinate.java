package com.characterBuilder.entities.pureDBEntities;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Data;

@Entity
@Table
@ManagedBean
@ApplicationScope
@Data
public class Coordinate {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COORDINATE_ID_SEQ")
	@SequenceGenerator(name = "COORDINATE_ID_SEQ", sequenceName = "COORDINATE_ID_SEQ")
	long id;
	
	@Column
	double longitude;
	
	@Column
	double latitude;

	public Coordinate() {
		super();
	}

	public Coordinate(long id, double longitude, double latitude) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
	}
}
