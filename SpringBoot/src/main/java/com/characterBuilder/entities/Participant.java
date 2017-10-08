package com.characterBuilder.entities;

import javax.annotation.ManagedBean;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.ids.ParticipantId;

import lombok.Data;

@Entity
@Table
@ManagedBean
@ApplicationScope
@Data
public class Participant implements Comparable<Participant>{

    @EmbeddedId
	private ParticipantId id;
	


	public Participant() {
		super();
	}
	
	public Participant(long eventTimeId, long userId) {
		super();
		this.id = new ParticipantId();
		setEventTimeId(eventTimeId);
		setUserId(userId);
	}
	
	public void setUserId(long id) {
		this.id.setUserId(id);
	}
	
	public long getUserId() {
		return this.id.getUserId();
	}
	
	public void setEventTimeId(long id) {
		this.id.setEventTimeId(id);
	}
	
	public long getEventTimeId() {
		return this.id.getEventTimeId();
	}

	/**
	 * Sorts by eventTimeId
	 */
	@Override
	public int compareTo(Participant o)
	{
		if(this.id.getEventTimeId() == o.getEventTimeId())
			return 0;
		
		return this.id.getEventTimeId() < o.getEventTimeId() ? -1 : 1;
	}
}
