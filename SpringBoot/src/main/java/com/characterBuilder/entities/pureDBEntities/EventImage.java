package com.characterBuilder.entities.pureDBEntities;

import java.util.Arrays;
import java.util.Comparator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT_IMAGE")
public class EventImage implements Comparable<Object>, Comparator<Object>{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_IMAGE_ID_SEQ")
	@SequenceGenerator(name = "EVENT_IMAGE_ID_SEQ", sequenceName = "EVENT_IMAGE_ID_SEQ")
	long id;
	
	@Column(name = "EVENT_ID")
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result + position;
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
		EventImage other = (EventImage) obj;
		if (eventId != other.eventId)
			return false;
		if (id != other.id)
			return false;
		if (!Arrays.equals(photo, other.photo))
			return false;
		if (position != other.position)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventImage [id=" + id + ", eventId=" + eventId + ", photo=" + Arrays.toString(photo) + ", position="
				+ position + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
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
