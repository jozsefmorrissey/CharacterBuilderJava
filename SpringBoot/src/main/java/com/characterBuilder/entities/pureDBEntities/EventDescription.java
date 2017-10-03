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
@Table(name = "EVENT_DESCRIPTION")
@ManagedBean
@ApplicationScope
@Data
public class EventDescription {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_DESCRIPTION_ID_SEQ")
	@SequenceGenerator(name = "EVENT_DESCRIPTION_ID_SEQ", sequenceName = "EVENT_DESCRIPTION_ID_SEQ")
	long id;
	
	@Column
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
}
