package com.characterBuilder.entities.abs;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;

import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Data;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@ManagedBean
@ApplicationScope
@Data
public abstract class ConstantAbs implements Comparable<ConstantAbs>
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONSTANT_ID_SEQ")
	@SequenceGenerator(name = "CONSTANT_ID_SEQ", sequenceName = "CONSTANT_ID_SEQ")
	private long id;
	
	@Column
	private String value;
	
	public ConstantAbs()
	{
		super();
	}

	public ConstantAbs(long id, String value)
	{
		this.id = id;
		this.value = value;
	}
	
	@Override
	public int compareTo(ConstantAbs o)
	{
		if(o == null)
			return -1;
		
		if(this.value == null) {
			if(o.value == null)
				return 0;
			return -1;
		}
		
		if(o.value == null)
			return 1;
		
		return value.compareTo(o.value);
	}
}
