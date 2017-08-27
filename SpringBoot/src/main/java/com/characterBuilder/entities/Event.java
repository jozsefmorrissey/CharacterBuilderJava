package com.characterBuilder.entities;

import java.util.List;

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

import com.characterBuilder.entities.pureDBEntities.EventImage;
import com.characterBuilder.entities.pureDBEntities.EventTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TODO: fix join relations
 * @author jozse
 *
 */
@Entity
@Table
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Event {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVENT_ID_SEQ")
	@SequenceGenerator(name = "EVENT_ID_SEQ", sequenceName = "EVENT_ID_SEQ")
	private long id;
	
	@Column
	String title;
	
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



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Description == null) ? 0 : Description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((images == null) ? 0 : images.hashCode());
		result = prime * result + ((poster == null) ? 0 : poster.hashCode());
		result = prime * result + ((times == null) ? 0 : times.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Event other = (Event) obj;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (id != other.id)
			return false;
		if (images == null) {
			if (other.images != null)
				return false;
		} else if (!images.equals(other.images))
			return false;
		if (poster == null) {
			if (other.poster != null)
				return false;
		} else if (!poster.equals(other.poster))
			return false;
		if (times == null) {
			if (other.times != null)
				return false;
		} else if (!times.equals(other.times))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", title=" + title + ", posterId=" + poster + ", times=" + times + ", images="
				+ images + ", Description=" + Description + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getPosterId() {
		return poster;
	}

	public void setPosterId(User posterId) {
		this.poster = posterId;
	}

	public List<EventTime> getTimes() {
		return times;
	}

	public void setTimes(List<EventTime> times) {
		this.times = times;
	}

	public List<EventImage> getImages() {
		return images;
	}

	public void setImages(List<EventImage> images) {
		this.images = images;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
