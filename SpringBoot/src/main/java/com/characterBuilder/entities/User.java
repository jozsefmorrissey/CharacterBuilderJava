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

import org.hibernate.annotations.Proxy;
import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.entities.pureDBEntities.Permission;
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
public class User {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
	@SequenceGenerator(name = "USER_ID_SEQ", sequenceName = "USER_ID_SEQ")
	long id;
	
	@Column
	String name;
	
	@Column
	String email;
	
	@Column
	Long phoneNumber;
	
	@OneToOne
	@JoinColumn(name = "PERMISSION_ID")
	Permission permission;
	
	@Column
	String password;
	
	@Column(name = "LAST_LOCATION_ID")
	int lastLocation;

	@Column
	byte[] photo;

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
