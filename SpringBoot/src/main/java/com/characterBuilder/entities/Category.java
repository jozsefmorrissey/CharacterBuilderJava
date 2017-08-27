package com.characterBuilder.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.characterBuilder.markers.HasId;

@Entity
@Table
public class Category implements Comparable<Category>, HasId{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
	@SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "CATEGORY_SEQ")
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String postDescription;
	
	@Column
	private String attendDescription;

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Category(long id, String name, String postDescription, String attendDescription) {
		super();
		this.id = id;
		this.name = name;
		this.postDescription = postDescription;
		this.attendDescription = attendDescription;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendDescription == null) ? 0 : attendDescription.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((postDescription == null) ? 0 : postDescription.hashCode());
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
		Category other = (Category) obj;
		if (attendDescription == null) {
			if (other.attendDescription != null)
				return false;
		} else if (!attendDescription.equals(other.attendDescription))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (postDescription == null) {
			if (other.postDescription != null)
				return false;
		} else if (!postDescription.equals(other.postDescription))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", postDescription=" + postDescription + ", attendDescription="
				+ attendDescription + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPostDescription() {
		return postDescription;
	}

	public void setPostDescription(String postDescription) {
		this.postDescription = postDescription;
	}

	public String getAttendDescription() {
		return attendDescription;
	}

	public void setAttendDescription(String attendDescription) {
		this.attendDescription = attendDescription;
	}

	@Override
	public int compareTo(Category o) {
		return this.name.compareTo(o.name);
	}
}
