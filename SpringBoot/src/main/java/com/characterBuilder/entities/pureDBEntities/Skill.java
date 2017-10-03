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
@Table
@ManagedBean
@ApplicationScope
@Data
public class Skill implements Comparable<Skill>
{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SKILL_ID_SEQ")
	@SequenceGenerator(name = "SKILL_ID_SEQ", sequenceName = "SKILL_ID_SEQ")
	long id;

	@Column(unique = true)
	String name;

	public Skill()
	{
		super();
	}

	public Skill(String name)
	{
		this.name = name;
	}

	@Override
	public int compareTo(Skill o)
	{
		return name.compareTo(o.name);
	}
}
