package com.characterBuilder.entities.pureDBEntities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.characterBuilder.entities.abs.ConstantAbs;

@Entity
@Table(name = "SKILL")
public class Skill extends ConstantAbs
{
	public Skill()
	{
		super();
	}

	public Skill(long id, String value)
	{
		super(id,value);
	}
}
