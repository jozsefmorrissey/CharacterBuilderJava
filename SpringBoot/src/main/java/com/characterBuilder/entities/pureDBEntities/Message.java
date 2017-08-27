package com.characterBuilder.entities.pureDBEntities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.characterBuilder.entities.User;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Sender_ID == null) ? 0 : Sender_ID.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		Message other = (Message) obj;
		if (Sender_ID == null) {
			if (other.Sender_ID != null)
				return false;
		} else if (!Sender_ID.equals(other.Sender_ID))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [Sender_ID=" + Sender_ID + ", message=" + message + ", dateTime=" + dateTime + "]";
	}

	public User getSender_ID() {
		return Sender_ID;
	}

	public void setSender_ID(User sender_ID) {
		Sender_ID = sender_ID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
}
