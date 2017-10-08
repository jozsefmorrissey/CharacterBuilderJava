package com.characterBuilder.entities.pureDBEntities;

import java.time.LocalDateTime;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.converter.LocalDateTimeConverter;
import com.characterBuilder.entities.User;

import lombok.Data;

@Entity
@Table(name = "USER_RATING")
@ManagedBean
@ApplicationScope
@Data
public class UserRating implements Comparable<UserRating>{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATING_SEQ")
	@SequenceGenerator(name = "RATING_SEQ", sequenceName = "RATING_SEQ")
	long id;
	
	@ManyToOne
	@JoinColumn(name = "ATTRIBUTER_ID")
	User attributer;
	
	@ManyToOne
	@JoinColumn(name = "RECIEVER_ID")
	User reciever;
	
	@Column
	String description;
	
	@Column(name = "TIME_STAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime dateTime;	
	
	@Column
	short value;
	
	@Column
	short attributerSnapshotRating;

	public UserRating() {
		super();
	}

	public UserRating(long id, User attributer, User reciever, String description, LocalDateTime dateTime, short value,
			short attributerSnapshotRating) {
		super();
		this.id = id;
		this.attributer = attributer;
		this.reciever = reciever;
		this.description = description;
		this.dateTime = dateTime;
		this.value = value;
		this.attributerSnapshotRating = attributerSnapshotRating;
	}

	@Override
	public int compareTo(UserRating arg0) {		
		return arg0.dateTime.compareTo(this.dateTime);
	}	
}
