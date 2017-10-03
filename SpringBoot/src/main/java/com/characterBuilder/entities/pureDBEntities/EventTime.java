package com.characterBuilder.entities.pureDBEntities;

import java.time.LocalDateTime;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.converter.LocalDateTimeConverter;

import lombok.Data;

@Entity
@Table(name = "EVENT_TIME")
@ManagedBean
@ApplicationScope
@Data
public class EventTime implements Comparable<EventTime> {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_TIME_ID_SEQ")
	@SequenceGenerator(name = "EVENT_TIME_ID_SEQ", sequenceName = "EVENT_TIME_ID_SEQ")
	long id;
	
	@Column
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
	public int compareTo(EventTime arg0) {
		if(arg0.getClass() != this.getClass())
			return 1;
			
		return this.getDateTime().compareTo(((EventTime)arg0).getDateTime());
	}
}
