package com.characterBuilder.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import com.characterBuilder.entities.pureDBEntities.EventImage;
import com.characterBuilder.entities.pureDBEntities.EventTime;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
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
	private User posterId;
	
	@OneToMany
	private Set<EventTime> times;
	
	@OneToMany
	private Set<EventImage> images;
	
	// This is to be retrieved separately.
	@Transient
	private String Description;
	
	public Event() {
		super();
	}

	public Event(long id, String title, User posterId, Set<EventTime> times, Set<EventImage> images,
			String description) {
		super();
		this.id = id;
		this.title = title;
		this.posterId = posterId;
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
		result = prime * result + ((posterId == null) ? 0 : posterId.hashCode());
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
		if (posterId == null) {
			if (other.posterId != null)
				return false;
		} else if (!posterId.equals(other.posterId))
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
		return "Event [id=" + id + ", title=" + title + ", posterId=" + posterId + ", times=" + times + ", images="
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
		return posterId;
	}

	public void setPosterId(User posterId) {
		this.posterId = posterId;
	}

	public Set<EventTime> getTimes() {
		return times;
	}

	public void setTimes(Set<EventTime> times) {
		this.times = times;
	}

	public Set<EventImage> getImages() {
		return images;
	}

	public void setImages(Set<EventImage> images) {
		this.images = images;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

}
