package com.characterBuilder.entities.pureDBEntities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.characterBuilder.entities.User;
import com.characterBuilder.util.LocalDateTimeConverter;

@Entity
@Table(name = "RATING")
public class Rating implements Comparable<Rating>{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATING_SEQ")
	@SequenceGenerator(name = "RATING_SEQ", sequenceName = "RATING_SEQ")
	long id;
	
	@ManyToOne
	@JoinColumn(name = "ATTRIBUTER_ID")
	User attributer;
	
	@ManyToOne
	@JoinColumn(name = "RECIEVER_ID")
	User reciever;
	
	@Column
	String description;
	
	@Column(name = "TIME_STAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime dateTime;	
	
	@Column
	short value;
	
	@Column
	short attributerSnapshotRating;

	public Rating() {
		super();
	}

	public Rating(long id, User attributer, User reciever, String description, LocalDateTime dateTime, short value,
			short attributerSnapshotRating) {
		super();
		this.id = id;
		this.attributer = attributer;
		this.reciever = reciever;
		this.description = description;
		this.dateTime = dateTime;
		this.value = value;
		this.attributerSnapshotRating = attributerSnapshotRating;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributer == null) ? 0 : attributer.hashCode());
		result = prime * result + attributerSnapshotRating;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((reciever == null) ? 0 : reciever.hashCode());
		result = prime * result + value;
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
		Rating other = (Rating) obj;
		if (attributer == null) {
			if (other.attributer != null)
				return false;
		} else if (!attributer.equals(other.attributer))
			return false;
		if (attributerSnapshotRating != other.attributerSnapshotRating)
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (reciever == null) {
			if (other.reciever != null)
				return false;
		} else if (!reciever.equals(other.reciever))
			return false;
		if (value != other.value)
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getAttributer() {
		return attributer;
	}

	public void setAttributer(User attributer) {
		this.attributer = attributer;
	}

	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public short getValue() {
		return value;
	}

	public void setValue(short value) {
		this.value = value;
	}

	public short getAttributerSnapshotRating() {
		return attributerSnapshotRating;
	}

	public void setAttributerSnapshotRating(short attributerSnapshotRating) {
		this.attributerSnapshotRating = attributerSnapshotRating;
	}
	
	@Override
	public int compareTo(Rating arg0) {		
		return arg0.dateTime.compareTo(this.dateTime);
	}	
}
