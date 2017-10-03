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
public class Permission {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSION_ID_SEQ")
	@SequenceGenerator(name = "PERMISSION_ID_SEQ", sequenceName = "PERMISSION_ID_SEQ")
	long id;
	
	@Column
	String PermissionLevel;

	public Permission() {
		super();
	}
}
