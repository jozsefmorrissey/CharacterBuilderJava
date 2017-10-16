package com.characterBuilder.entities.pureDBEntities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.characterBuilder.entities.abs.RatingAbs;

@Entity
@Table(name = "EVENT_RATING")
public class EventRating 
		extends RatingAbs {
	
	public EventRating() {
		super();
	}
	public EventRating(long id, long attributer, long reciever, String description, LocalDateTime dateTime, short value,
			short attributerSnapshotRating) {
		super();
		this.setId(id);
		this.setAttributer(attributer);
		this.setReciever(reciever);
		this.setDescription(description);
		this.setDateTime(dateTime);
		this.setValue(value);
		this.setAttributerSnapshotRating(attributerSnapshotRating);
	}
}
