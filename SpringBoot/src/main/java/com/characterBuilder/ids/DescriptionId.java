package com.characterBuilder.ids;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import lombok.Data;

@Embeddable
@Table
@ManagedBean
@ApplicationScope
@Data
public class DescriptionId implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2163537859748691591L;

	@Column
	private long id;
	
	@Column
	private int position;
}
