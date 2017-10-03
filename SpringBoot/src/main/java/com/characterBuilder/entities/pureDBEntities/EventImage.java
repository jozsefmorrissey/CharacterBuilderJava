package com.characterBuilder.entities.pureDBEntities;

import java.util.Comparator;

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
@Table(name = "EVENT_IMAGE")
@ManagedBean
@ApplicationScope
@Data
public class EventImage implements Comparable<Object>, Comparator<Object>{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_IMAGE_ID_SEQ")
	@SequenceGenerator(name = "EVENT_IMAGE_ID_SEQ", sequenceName = "EVENT_IMAGE_ID_SEQ")
	long id;
	
	@Column
	long eventId;
	
	@Column
	byte[] photo;
	
	@Column
	int position;

	public EventImage() {
		super();
	}
	
	public EventImage(EventImage other) {
		this(other.id, other.eventId, other.photo, other.position);
	}

	public EventImage(long id, long eventId, byte[] photo, int position) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.photo = photo;
		this.position = position;
	}

	/**
	 * Order by position
	 */
	@Override
	public int compareTo(Object arg0) {
		if(arg0.getClass() != this.getClass())
			return 1;
		EventImage other = (EventImage)arg0;
		return other.position - this.position;
	}

	/**
	 * Order by id
	 */
	@Override
	public int compare(Object arg0, Object arg1) {
		if(arg0.getClass() != this.getClass())
			return -1;
		if(arg1.getClass() != this.getClass())
			return 1;
		
		long id0 = ((EventImage)arg0).getId();
		long id1 = ((EventImage)arg1).getId();
		if(id0 == id1)
			return 0;
		return id0 - id1 > 0 ? 1 : -1;
	}
}
