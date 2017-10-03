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
import com.characterBuilder.markers.HasDateTime;
import com.characterBuilder.markers.HasId;

import lombok.Data;

@Table(name = "MESSAGE")
@Entity
@ManagedBean
@ApplicationScope
@Data
public class UserMessage implements HasDateTime, HasId, Comparable<UserMessage> {
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MESSAGE_ID_SEQ")
	@SequenceGenerator(name = "MESSAGE_ID_SEQ", sequenceName = "MESSAGE_ID_SEQ")
	private long id;
	
	@Column(name = "SENDER_ID")
	private long senderId;
	
	@Column(name = "RECIEVER_ID")
	private long recipientId;
	
	@Column
	private String message;
	
	@Column(name = "TIME_STAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime dateTime;

	public UserMessage() {
		super();
	}

	public UserMessage(long id, long senderId, long recipientId, String message, LocalDateTime dateTime)
	{
		super();
		this.id = id;
		this.senderId = senderId;
		this.recipientId = recipientId;
		this.message = message;
		this.dateTime = dateTime;
	}
	
	@Override
	public String toString()
	{
		return "UserMessage [id=" + id + ", Sender_ID=" + senderId + ", reciever=" + recipientId + ", message=" + message
				+ ", dateTime=" + dateTime + "]";
	}

	/**
	 * Sorts by sender and receiver then time.
	 */
	@Override
	public int compareTo(UserMessage arg0)
	{
		if(arg0 == null)
			return 1;
		
		int emailCmp = emailCompare(arg0);
		if(emailCmp == 0){
			return this.dateTime.compareTo(arg0.dateTime);
		}
		
		return emailCmp;
	}
	
	/**
	 * @param arg0
	 * @return 0 if sender and receiver are equal
	 */
	public int emailCompare(UserMessage arg0) {
		String thisUniqueStr = createUniqueConvsationStr(this);
		String arg0UniqueStr = createUniqueConvsationStr(arg0);
		
		return -1 * thisUniqueStr.compareTo(arg0UniqueStr);
	}
	
	private String createUniqueConvsationStr(UserMessage arg0) {
		String lowIdUniqueStr;
		if(arg0.recipientId < arg0.senderId) {
			lowIdUniqueStr = arg0.recipientId + ":" + arg0.senderId;
		}
		else {
			lowIdUniqueStr = arg0.senderId + ":" + arg0.recipientId;
		}
		return lowIdUniqueStr;
	}
	
	public boolean isSameConversation(UserMessage arg0) {
		return emailCompare(arg0) == 0;
	}
}
