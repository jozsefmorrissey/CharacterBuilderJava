package com.characterBuilder.srvc.interfaces;

import java.util.List;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.ProfileLink;

public interface ProfileLinkSrvc {
	public List<ProfileLink> getAll(User user);
	public List<ProfileLink> getAllByCreator(User user);
	
	public void addLink(ProfileLink profileLink);
	
	public void removeLink(ProfileLink profileLink);
}
