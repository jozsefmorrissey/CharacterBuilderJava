package com.characterBuilder.entities.pureDBEntities;

import javax.annotation.ManagedBean;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.entities.User;
import com.characterBuilder.ids.ProfileLinkId;
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
	@EmbeddedId
	ProfileLinkId id;
	
	@Column
	Boolean isGood;
	
	@Column
	String reason;

	public ProfileLink() {
		super();
	}

	public ProfileLink(User user1, User user2, User creator, Boolean isGood, String reason) {
		super();
		this.id = new ProfileLinkId();
		this.id.setUser1(user1);
		this.id.setUser2(user2);
		this.id.setCreator(creator);
		this.isGood = isGood;
		this.reason = reason;
	}

	public void setUser1(User user) {
		id.setUser1(user);
	}
	
	public User getUser1() {
		return id.getUser1();
	}
	
	public void setUser2(User user) {
		id.setUser2(user);
	}
	
	public User getUser2() {
		return id.getUser2();
	}
	
	public void setCreator(User user) {
		id.setCreator(user);
	}
	
	public User getCreator() {
		return id.getCreator();
	}
}
