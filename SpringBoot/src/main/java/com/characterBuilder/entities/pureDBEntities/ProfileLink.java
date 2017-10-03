package com.characterBuilder.entities.pureDBEntities;

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

import com.characterBuilder.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Table(name = "PROFILE_LINK")
@ManagedBean
@ApplicationScope
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Proxy(lazy = false)
public class ProfileLink {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFILE_LINK_SEQ")
	@SequenceGenerator(name = "PROFILE_LINK_SEQ", sequenceName = "PROFILE_LINK_SEQ")
	long id;
	
	@OneToOne
	@JoinColumn(name = "USER_ID_1")
	User user1;

	@OneToOne
	@JoinColumn(name = "USER_ID_2" )
	User user2;
	
	@OneToOne
	@JoinColumn(name = "CREATOR_ID")
	User creator;
	
	@Column
	Boolean isGood;
	
	@Column
	String reason;

	public ProfileLink() {
		super();
	}

	public ProfileLink(long id, User user1, User user2, User creator, Boolean isGood, String reason) {
		super();
		this.id = id;
		this.user1 = user1;
		this.user2 = user2;
		orderUsers();
		this.creator = creator;
		this.isGood = isGood;
		this.reason = reason;
	}

	/**
	 * 	Order doesn't matter to the link itself, but this simplifies the verification that
	 *  a link has not been previously submitted by the given creator.
	 */
	private void orderUsers(){
		if(user1 == null && user2 == null)
			return;
		if(user1 == null)
			user1 = user2;
		if(user1 == null)
			return;
		
		if(user1.getId() > user2.getId()) {
			User temp = user1;
			this.user1 = user2;
			this.user2 = temp;
		}
	}
}
