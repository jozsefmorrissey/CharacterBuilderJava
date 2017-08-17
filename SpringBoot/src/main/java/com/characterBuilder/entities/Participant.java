package com.characterBuilder.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.ReadOnlyProperty;

@Entity
@Table
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

	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eventTimeId ^ (eventTimeId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((participant == null) ? 0 : participant.hashCode());
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
		Participant other = (Participant) obj;
		if (eventTimeId != other.eventTimeId)
			return false;
		if (id != other.id)
			return false;
		if (participant == null) {
			if (other.participant != null)
				return false;
		} else if (!participant.equals(other.participant))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Participant [id=" + id + ", eventTimeId=" + eventTimeId + ", participant=" + participant + "]";
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEventTimeId() {
		return eventTimeId;
	}

	public void setEventTimeId(long eventTimeId) {
		this.eventTimeId = eventTimeId;
	}

	public User getParticipant() {
		return participant;
	}

	public void setParticipant(User participant) {
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
