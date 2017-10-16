package com.characterBuilder.entities.pureDBEntities;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.characterBuilder.entities.abs.ConstantAbs;

@Entity
@Table(name = "USER_FLAG")
public class UserFlag extends ConstantAbs
{
	public UserFlag()
	{
		super();
	}

	public UserFlag(long id, String value)
	{
		super(id,value);
	}
}
