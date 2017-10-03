package com.characterBuilder.entities.pureDBEntities;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Data;

@Entity
@Table
@ManagedBean
@ApplicationScope
@Data
public class Description {
	@Id
	@Column
	long id;
	
	@Column
	String text;

	public Description() {
		super();
	}
}
