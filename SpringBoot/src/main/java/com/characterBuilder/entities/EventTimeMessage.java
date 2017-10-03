package com.characterBuilder.entities;

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
import com.characterBuilder.converter.NullToZeroConverter;
import com.characterBuilder.markers.HasDateTime;
import com.characterBuilder.markers.HasId;
import com.characterBuilder.markers.Message;

import lombok.Data;

@Table(name = "EVENT_TIME_MESSAGE")
@Entity
@ManagedBean
@ApplicationScope
@Data
public class EventTimeMessage implements HasDateTime, HasId, Message {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_ID_SEQ")
	@SequenceGenerator(name = "MESSAGE_ID_SEQ", sequenceName = "MESSAGE_ID_SEQ")
	long id;
	
	@Column(name = "SENDER_ID")
	long senderId;
	
	@Column(name = "EVENT_TIME_ID")
	long eventTimeId;
	
	@Column(name = "RECIPIENT_ID")
	@Convert(converter = NullToZeroConverter.class)
	long recipientId;
	
	@Column
	String message;
	
	@Column(name = "TIME_STAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime dateTime;

	public EventTimeMessage() {
		super();
	}

	public EventTimeMessage(long id, long senderId, long eventTimeId, String message, LocalDateTime dateTime)
	{
		super();
		this.id = id;
		this.senderId = senderId;
		this.eventTimeId = eventTimeId;
		this.message = message;
		this.dateTime = dateTime;
	}
}
