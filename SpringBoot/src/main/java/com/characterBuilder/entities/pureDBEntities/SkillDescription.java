package com.characterBuilder.entities.pureDBEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SKILL_DESCRIPTION")
public class SkillDescription {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SKILL_DESCRIPTION_ID_SEQ")
	@SequenceGenerator(name = "SKILL_DESCRIPTION_ID_SEQ", sequenceName = "SKILL_DESCRIPTION_ID_SEQ")
	long id;
	
	@Column(name = "SKILL_MAP_ID")
	long skillMapId;
	
	@Column
	String description;
	
	@Column
	int position;

	public SkillDescription() {
		super();
	}

	public SkillDescription(long id, long skillMapId, String description, int position) {
		super();
		this.id = id;
		this.skillMapId = skillMapId;
		this.description = description;
		this.position = position;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result + position;
		result = prime * result + (int) (skillMapId ^ (skillMapId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
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
		SkillDescription other = (SkillDescription) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (position != other.position)
			return false;
		if (skillMapId != other.skillMapId)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SkillDescription [id=" + id + ", SkillMapId=" + skillMapId + ", Description=" + description
				+ ", Position=" + position + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSkillMapId() {
		return skillMapId;
	}

	public void setSkillMapId(long skillMapId) {
		this.skillMapId = skillMapId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}
