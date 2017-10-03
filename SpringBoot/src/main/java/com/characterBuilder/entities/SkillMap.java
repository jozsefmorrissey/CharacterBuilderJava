package com.characterBuilder.entities;

import javax.annotation.ManagedBean;
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

import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.entities.pureDBEntities.Skill;

import lombok.Data;

@Entity
@Table(name = "SKILL_MAP")
@ManagedBean
@ApplicationScope
@Data
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
}
