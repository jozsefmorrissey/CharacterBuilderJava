package com.characterBuilder.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.pureDBEntities.Description;
import com.characterBuilder.repositories.DescriptionRepo;
import com.characterBuilder.services.interfaces.DescriptionSrvc;

@Service
public class DescriptionSrvcImpl implements DescriptionSrvc {
	
	@Autowired
	DescriptionRepo descRepo;

	/**
	 * The repo function returns and list of Description elements 
	 * containing only one row.
	 */
	@Override
	public String getEventDescription(long id) {
		List<Description> desc = descRepo.getEventDesc(id);
		if(desc.size() < 1)
			return null;
		return desc.get(0).getText();
	}

	@Override
	public List<Description> getAllEventDesc() {
		return descRepo.getAllEventDesc();
	}

	@Override
	public String getSkillDescription(long id) {
		List<Description> desc = descRepo.getSkillDesc(id);
		if(desc.size() < 1)
			return null;
		return desc.get(0).getText();
	}

	@Override
	public List<Description> getAllSkillDesc() {
		return descRepo.getAllSkillDesc();
	}

	
}
