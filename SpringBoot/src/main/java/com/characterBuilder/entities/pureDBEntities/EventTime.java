package com.characterBuilder.entities.pureDBEntities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.characterBuilder.util.LocalDateTimeConverter;

@Entity
@Table(name = "EVENT_TIME")
public class EventTime implements Comparable<EventTime> {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_TIME_ID_SEQ")
	@SequenceGenerator(name = "EVENT_TIME_ID_SEQ", sequenceName = "EVENT_TIME_ID_SEQ")
	long id;
	
	@Column(name = "EVENT_ID")
	long eventId;
	
	@Column(name = "TIME_STAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime dateTime;

	public EventTime() {
		super();
	}

	public EventTime(long id, long eventId, LocalDateTime dateTime) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.dateTime = dateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
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
		EventTime other = (EventTime) obj;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (eventId != other.eventId)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventTime [id=" + id + ", eventId=" + eventId + ", dateTime=" + dateTime + "]";
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

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public int compareTo(EventTime arg0) {
		if(arg0.getClass() != this.getClass())
			return 1;
			
		return this.getDateTime().compareTo(((EventTime)arg0).getDateTime());
	}

}
