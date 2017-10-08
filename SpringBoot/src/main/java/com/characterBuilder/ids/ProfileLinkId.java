package com.characterBuilder.ids;

import java.io.Serializable;

import javax.annotation.ManagedBean;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.web.context.annotation.ApplicationScope;

import com.characterBuilder.entities.User;

import lombok.Data;

@Embeddable
@Table
@ManagedBean
@ApplicationScope
@Data
public class ProfileLinkId implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3107847662387791913L;

	@OneToOne
	@JoinColumn(name = "USER_ID_1")
	User user1;

	@OneToOne
	@JoinColumn(name = "USER_ID_2" )
	User user2;
	
	@OneToOne
	@JoinColumn(name = "CREATOR_ID")
	User creator;
	
	public void setUser1(User user) {
		this.user1 = user;
		orderUsers();
	}
	
	public void setUser2(User user) {
		this.user2 = user;
		orderUsers();
	}
	
	/**
	 * 	Order doesn't matter to the link itself, but this simplifies the verification that
	 *  a link has not been previously submitted by the given creator.
	 */
	public void orderUsers(){
		if(getUser1() == null && getUser2() == null)
			return;
		if(getUser1() == null) {
			setUser1(getUser2());
		}
		
		if(getUser2() == null)
			return;
		
		if(getUser1().getId() > getUser2().getId()) {
			User temp = getUser1();
			setUser1(getUser2());
			setUser2(temp);
		}
	}
}
