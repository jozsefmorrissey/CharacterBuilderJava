package com.characterBuilder.entities;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;
import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.entities.pureDBEntities.Permission;
import com.characterBuilder.markers.HasIdDesc;
import com.characterBuilder.util.ImageUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;


/**
 * TODO: Implement a description(About Me) field
 * @author jozse
 *
 */
@Entity
@Table(name = "CB_USER")
@ManagedBean
@ApplicationScope
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
public class User implements HasIdDesc{
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
	@SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ")
	private long id;
	
	@Column
	private String name;
	
	@Column
	private String email;
	
	@Column
	private Long phoneNumber;
	
	@OneToOne
	@JoinColumn(name = "PERMISSION_ID")
	private Permission permission;
	
	@Column
	private String password;
	
	@Column(name = "LAST_LOCATION_ID")
	private int lastLocation;

	@Column
	private byte[] photo;
	
	// This is to be retrieved separately.
	@Transient
	private String Description;

	public User() {
		super();
	}
	
	public User(long id, String name, String email, Long phoneNumber, 
			Permission permission, String password, int lastLocation) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.permission = permission;
		this.password = password;
		this.photo = ImageUtils.convertToBytes("src/main/resources/static/images/default-image.jpg");
	}

	public User(long id, String name, String email, Long phoneNumber, Permission permission, String password
			, int lastLocation, byte[] photo) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.permission = permission;
		this.password = password;
		this.photo = photo;
	}
}
