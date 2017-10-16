package com.characterBuilder.entities.abs;

import java.time.LocalDateTime;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.converter.LocalDateTimeConverter;

import lombok.Data;

@MappedSuperclass
@ManagedBean
@ApplicationScope
@Data
public abstract class RatingAbs implements Comparable<RatingAbs>
{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RATING_SEQ")
	@SequenceGenerator(name = "RATING_SEQ", sequenceName = "RATING_SEQ")
	long id;
	
	@Column(name = "ATTRIBUTER_ID")
	long attributer;
	
	@Column(name = "RECIEVER_ID")
	long reciever;
	
	@Column
	String description;
	
	@Column(name = "TIME_STAMP")
	@Convert(converter = LocalDateTimeConverter.class)
	LocalDateTime dateTime;	
	
	@Column
	short value;
	
	@Column
	short attributerSnapshotRating;

	public RatingAbs() {
		super();
	}

	public RatingAbs(long id, long attributer, long reciever, String description, LocalDateTime dateTime, short value,
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
	public int compareTo(RatingAbs arg0) {		
		return arg0.dateTime.compareTo(this.dateTime);
	}	
}
