package com.characterBuilder.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.characterBuilder.entities.pureDBEntities.Coordinate;

@Entity
@Table
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + radiusMeters;
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (radiusMeters != other.radiusMeters)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", userId=" + userId + ", coordinate=" + coordinate + ", name=" + name
				+ ", radiusMeters=" + radiusMeters + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRadiusMeters() {
		return radiusMeters;
	}

	public void setRadiusMeters(int radiusMeters) {
		this.radiusMeters = radiusMeters;
	}

	@Override
	public int compareTo(Location arg0) {
		return this.name.compareTo(arg0.getName());
	}
}
