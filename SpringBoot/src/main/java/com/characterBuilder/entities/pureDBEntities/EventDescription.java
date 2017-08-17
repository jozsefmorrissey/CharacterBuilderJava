package com.characterBuilder.entities.pureDBEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "EVENT_DESCRIPTION")
public class EventDescription {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_DESCRIPTION_ID_SEQ")
	@SequenceGenerator(name = "EVENT_DESCRIPTION_ID_SEQ", sequenceName = "EVENT_DESCRIPTION_ID_SEQ")
	long id;
	
	@Column(name = "event_id")
	long eventId;
	
	@Column
	String description;
	
	@Column
	int position;

	public EventDescription() {
		super();
	}
	

	public EventDescription(long id, long eventId, String description, int position) {
		super();
		this.id = id;
		this.description = description;
		this.position = position;
		this.eventId = eventId;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
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
		EventDescription other = (EventDescription) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eventId != other.eventId)
			return false;
		if (id != other.id)
			return false;
		if (position != other.position)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "EventDescription [id=" + id + ", eventId=" + eventId + ", Description=" + description + ", position="
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


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getPosition() {
		return position;
	}


	public void setPosition(int position) {
		this.position = position;
	}
}
