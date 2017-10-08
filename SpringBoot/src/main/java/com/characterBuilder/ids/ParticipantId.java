package com.characterBuilder.ids;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Data;

@Embeddable
@Table
@ManagedBean
@ApplicationScope
@Data
public class ParticipantId implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6444521881297369644L;

	@Column(name = "USER_ID")
	private long userId;

	@Column(name = "EVENT_TIME_ID")
	private long eventTimeId;
	
}
