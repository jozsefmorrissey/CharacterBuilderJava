package com.characterBuilder.entities;

import java.util.List;

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
import javax.persistence.Transient;

import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.entities.pureDBEntities.EventImage;
import com.characterBuilder.entities.pureDBEntities.EventTime;
import com.characterBuilder.markers.HasIdDesc;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * TODO: fix join relations
 * @author jozse
 *
 */
@Entity
@Table
@ManagedBean
@ApplicationScope
@Data
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Event implements HasIdDesc{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_ID_SEQ")
	@SequenceGenerator(name = "EVENT_ID_SEQ", sequenceName = "EVENT_ID_SEQ")
	private long id;
	
	@Column
	private String title;
	
	@OneToOne
	@JoinColumn(name = "POSTER_ID")
	private User poster;
	
	// TODO: hibernate this have to rework EventTime obj and srvc test
	@Transient
	private List<EventTime> times;
	
	// TODO: hibernate this have to rework EventImage obj and srvc test
	@Transient
	private List<EventImage> images;
	
	// This is to be retrieved separately.
	@Transient
	private String Description;
	
	public Event() {
		super();
	}

	public Event(long id, String title, User posterId, List<EventTime> times, List<EventImage> images,
			String description) {
		super();
		this.id = id;
		this.title = title;
		this.poster = posterId;
		this.times = times;
		this.images = images;
		Description = description;
	}
}
