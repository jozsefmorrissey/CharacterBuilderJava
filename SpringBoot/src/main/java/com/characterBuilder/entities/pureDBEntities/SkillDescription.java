package com.characterBuilder.entities.pureDBEntities;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Data;

@Entity
@Table(name = "SKILL_DESCRIPTION")
@ManagedBean
@ApplicationScope
@Data
public class SkillDescription {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SKILL_DESCRIPTION_ID_SEQ")
	@SequenceGenerator(name = "SKILL_DESCRIPTION_ID_SEQ", sequenceName = "SKILL_DESCRIPTION_ID_SEQ")
	long id;
	
	@Column
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
}
