package com.characterBuilder.entities.pureDBEntities;

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

import com.characterBuilder.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "PROFILE_LINK")
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
		// TODO Auto-generated constructor stub
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((isGood == null) ? 0 : isGood.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + ((user1 == null) ? 0 : user1.hashCode());
		result = prime * result + ((user2 == null) ? 0 : user2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProfileLink other = (ProfileLink) obj;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (id != other.id)
			return false;
		if (isGood == null) {
			if (other.isGood != null)
				return false;
		} else if (!isGood.equals(other.isGood))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (user1 == null) {
			if (other.user1 != null)
				return false;
		} else if (!user1.equals(other.user1))
			return false;
		if (user2 == null) {
			if (other.user2 != null)
				return false;
		} else if (!user2.equals(other.user2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProfileLink [id=" + id + ", user1=" + user1 + ", user2=" + user2 + ", creator=" + creator + ", isGood="
				+ isGood + ", reason=" + reason + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		orderUsers();
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		orderUsers();
		this.user2 = user2;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Boolean getIsGood() {
		return isGood;
	}

	public void setIsGood(Boolean isGood) {
		this.isGood = isGood;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
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
