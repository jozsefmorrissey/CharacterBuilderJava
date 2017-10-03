package com.characterBuilder.entities;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Data;

@Entity
@Table
@ManagedBean
@ApplicationScope
@Data
public class Participant implements Comparable<Participant>{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PARTICIPANT_ID_SEQ")
	@SequenceGenerator(name = "PARTICIPANT_ID_SEQ", sequenceName = "PARTICIPANT_ID_SEQ")
	private long id;
	
	@Column
	private long eventTimeId;
	
	@OneToOne
	@JoinColumn(name = "USER_ID", updatable = false)
	private User participant;

	public Participant() {
		super();
	}
	
	public Participant(long id, long eventTimeId, User participant) {
		super();
		this.id = id;
		this.eventTimeId = eventTimeId;
		this.participant = participant;
	}

	/**
	 * Sorts in alphabetical order by participant.name
	 */
	@Override
	public int compareTo(Participant arg0) {
		return this.getParticipant().getName().compareTo(arg0.getParticipant().getName());
	}
}
