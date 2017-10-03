package com.characterBuilder.entities;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.entities.pureDBEntities.Coordinate;

import lombok.Data;

@Entity
@Table
@ManagedBean
@ApplicationScope
@Data
public class Location implements Comparable<Location> {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOCATION_ID_SEQ")
	@SequenceGenerator(name = "LOCATION_ID_SEQ", sequenceName = "LOCATION_ID_SEQ")
	private long id;
	
	@Column(name = "USER_ID")
	long userId;
	
	@ManyToOne
	@JoinColumn(name = "COORDINATE_ID")
	private Coordinate coordinate;
	
	@Column
	private String name;
	
	@Column
	private int radiusMeters;

	public Location() {
		super();
	}

	public Location(long id, long userId, Coordinate coordinate, String name, int radiusMeters) {
		super();
		this.id = id;
		this.userId = userId;
		this.coordinate = coordinate;
		this.name = name;
		this.radiusMeters = radiusMeters;
	}

	@Override
	public int compareTo(Location arg0) {
		return this.name.compareTo(arg0.getName());
	}
}
