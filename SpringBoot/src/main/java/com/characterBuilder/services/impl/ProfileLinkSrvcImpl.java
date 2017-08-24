package com.characterBuilder.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.ProfileLink;
import com.characterBuilder.repositories.ProfileLinkRepo;
import com.characterBuilder.services.interfaces.ProfileLinkSrvc;

@Service
public class ProfileLinkSrvcImpl implements ProfileLinkSrvc {

	@Autowired
	ProfileLinkRepo proLinkRepo;
	
	@Override
	@Transactional
	public List<ProfileLink> getAll(User user) {
		return proLinkRepo.findNatural(user.getId());
	}

	@Override
	@Transactional
	public void addLink(ProfileLink profileLink) {
		proLinkRepo.save(profileLink);
	}

	@Override
	@Transactional
	public void removeLink(ProfileLink profileLink) {
		proLinkRepo.delete(profileLink);
	}

	@Override
	public List<ProfileLink> getAllByCreator(User user) {
		return proLinkRepo.findByCreator(user);
	}
}
