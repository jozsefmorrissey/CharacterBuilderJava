package com.characterBuilder.entities;

import java.time.LocalDateTime;

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

import com.characterBuilder.entities.pureDBEntities.Message;

import lombok.Data;

@Table(name = "MESSAGE")
@Entity
@ManagedBean
@ApplicationScope
@Data
public class UserMessage extends Message {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_ID_SEQ")
	@SequenceGenerator(name = "MESSAGE_ID_SEQ", sequenceName = "MESSAGE_ID_SEQ")
	long id;
	
	@OneToOne
	@JoinColumn(name = "RECIEVER_ID")
	User reciever;

	public UserMessage() {
		super();
	}

	public UserMessage(long id, User sender, User reciever, String message, LocalDateTime dateTime) {
		super(sender, message, dateTime);
		this.reciever = reciever;
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((reciever == null) ? 0 : reciever.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserMessage other = (UserMessage) obj;
		if (id != other.id)
			return false;
		if (reciever == null) {
			if (other.reciever != null)
				return false;
		} else if (!reciever.equals(other.reciever))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserMessage [id=" + id + ", reciever=" + reciever + "]";
	}
}
