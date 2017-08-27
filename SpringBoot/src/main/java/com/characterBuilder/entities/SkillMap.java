package com.characterBuilder.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.characterBuilder.entities.pureDBEntities.Skill;

@Entity
@Table(name = "SKILL_MAP")
public class SkillMap {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SKILL_MAP_ID_SEQ")
	@SequenceGenerator(name = "SKILL_MAP_ID_SEQ", sequenceName = "SKILL_MAP_ID_SEQ")
	private long id;
	
    @ManyToOne(optional=false) 
    @JoinColumn(name="SKILL_ID", nullable=false, updatable=false)
	private Skill skill;
	
	@Column
	private long eventId;
	
	@OneToOne
	@JoinColumn(name = "ATTRIBUTER_ID")
	private User attributer;
	
	@OneToOne
	@JoinColumn(name = "RECIEVER_ID")
	private User reciever;
	
	@Column
	private Short value;
	
	@Column(name = "ATTR_SKILL_SNP_SHT_VALUE")
	private Short attrValue;

	// This is to be retrieved separately.
	@Transient
	private String description;

	public SkillMap(){
		super();
	}

	public SkillMap(long id, Skill skill, long eventId, User attributer, User reciever, Short value, Short attrValue,
			String description) {
		super();
		this.id = id;
		this.skill = skill;
		this.eventId = eventId;
		this.attributer = attributer;
		this.reciever = reciever;
		this.value = value;
		this.attrValue = attrValue;
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attrValue == null) ? 0 : attrValue.hashCode());
		result = prime * result + ((attributer == null) ? 0 : attributer.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((reciever == null) ? 0 : reciever.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		SkillMap other = (SkillMap) obj;
		if (attrValue == null) {
			if (other.attrValue != null)
				return false;
		} else if (!attrValue.equals(other.attrValue))
			return false;
		if (attributer == null) {
			if (other.attributer != null)
				return false;
		} else if (!attributer.equals(other.attributer))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eventId != other.eventId)
			return false;
		if (id != other.id)
			return false;
		if (reciever == null) {
			if (other.reciever != null)
				return false;
		} else if (!reciever.equals(other.reciever))
			return false;
		if (skill == null) {
			if (other.skill != null)
				return false;
		} else if (!skill.equals(other.skill))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SkillMap [id=" + id + ", skill=" + skill + ", eventId=" + eventId + ", attributer=" + attributer
				+ ", reciever=" + reciever + ", value=" + value + ", attrValue=" + attrValue + ", description="
				+ description + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public User getAttributer() {
		return attributer;
	}

	public void setAttributer(User attributer) {
		this.attributer = attributer;
	}

	public User getReciever() {
		return reciever;
	}

	public void setReciever(User reciever) {
		this.reciever = reciever;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}

	public Short getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(Short attrValue) {
		this.attrValue = attrValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
