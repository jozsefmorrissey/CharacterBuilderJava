package com.characterBuilder.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.abs.DescriptionSrvcAbs;
import com.characterBuilder.entities.SkillMap;
import com.characterBuilder.entities.pureDBEntities.Description;
import com.characterBuilder.entities.pureDBEntities.SkillDescription;
import com.characterBuilder.markers.DescriptionRepoMarker;
import com.characterBuilder.repositories.SkillDescriptionRepo;
import com.characterBuilder.services.interfaces.SkillDescriptionSrvc;

@Service
public class SkillDescriptionSrvcImpl
		extends DescriptionSrvcAbs<SkillDescription, SkillMap> 
		implements SkillDescriptionSrvc {
	
	@Autowired
	SkillDescriptionRepo skillDescRepo;
	
	protected DescriptionRepoMarker<SkillDescription> getDescRepo() {
		return this.skillDescRepo;
	}

	@Override
	public List<Description> getAllDescriptions()
	{
		return getDescSrvc().getAllSkillDesc();
	}

	@Override
	protected String getDescription(long id)
	{
		return getDescSrvc().getSkillDescription(id);
	}
	
	@Override
	protected SkillDescription create(long id, String description, int position) {
		return new SkillDescription(id, description, position);
	}
}