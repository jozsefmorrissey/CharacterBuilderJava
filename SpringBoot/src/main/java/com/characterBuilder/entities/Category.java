package com.characterBuilder.entities;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.markers.HasId;

import lombok.Data;

@Entity
@Table
@ManagedBean
@ApplicationScope
@Data
public class Category implements Comparable<Category>, HasId{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_SEQ")
	@SequenceGenerator(name = "CATEGORY_SEQ", sequenceName = "CATEGORY_SEQ")
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String postDescription;
	
	@Column
	private String attendDescription;

	public Category() {
		super();
	}

	public Category(long id, String name, String postDescription, String attendDescription) {
		super();
		this.id = id;
		this.name = name;
		this.postDescription = postDescription;
		this.attendDescription = attendDescription;
	}

	@Override
	public int compareTo(Category o) {
		return this.name.compareTo(o.name);
	}
}
