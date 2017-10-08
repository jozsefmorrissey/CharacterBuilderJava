package com.characterBuilder.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.characterBuilder.abs.DescriptionSrvcAbs;
import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.Description;
import com.characterBuilder.entities.pureDBEntities.UserDescription;
import com.characterBuilder.markers.DescriptionRepoMarker;
import com.characterBuilder.repositories.UserDescriptionRepo;
import com.characterBuilder.services.interfaces.UserDescriptionSrvc;

public class UserDescriptionSrvcImpl 
		extends DescriptionSrvcAbs<UserDescription, User> 
		implements UserDescriptionSrvc {

	@Autowired
	UserDescriptionRepo userDescRepo;
	
	protected DescriptionRepoMarker<UserDescription> getDescRepo() {
	return this.userDescRepo;
	}
	
	@Override
	public List<Description> getAllDescriptions()
	{
	return getDescSrvc().getAllUserDesc();
	}
	
	@Override
	protected String getDescription(long id)
	{
		return getDescSrvc().getUserDescription(id);
	}
	
	@Override
	protected UserDescription create(long id, String description, int position) {
		return new UserDescription(id, description, position);
	}
}
