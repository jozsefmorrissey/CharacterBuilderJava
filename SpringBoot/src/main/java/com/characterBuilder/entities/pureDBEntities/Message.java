package com.characterBuilder.entities.pureDBEntities;

import java.time.LocalDateTime;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.entities.User;

import lombok.Data;

@ManagedBean
@ApplicationScope
@Data
public abstract class Message {


	@OneToOne
	@JoinColumn
	User Sender_ID;
	
	@Column
	String message;
	
	@Column(name = "TIME_STAMP")
	LocalDateTime dateTime;

	public Message() {
		super();
	}

	public Message(User sender_ID, String message, LocalDateTime dateTime) {
		super();
		Sender_ID = sender_ID;
		this.message = message;
		this.dateTime = dateTime;
	}
}
