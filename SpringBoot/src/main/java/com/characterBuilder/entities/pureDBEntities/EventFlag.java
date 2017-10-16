package com.characterBuilder.entities.pureDBEntities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.characterBuilder.entities.abs.ConstantAbs;

@Entity
@Table(name = "EVENT_FLAG")
public class EventFlag extends ConstantAbs
{
	public EventFlag()
	{
		super();
	}

	public EventFlag(long id, String value)
	{
		super(id,value);
	}
}
