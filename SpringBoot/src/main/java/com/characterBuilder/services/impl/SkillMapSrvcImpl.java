package com.characterBuilder.services.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.SkillMap;
import com.characterBuilder.entities.User;
import com.characterBuilder.repositories.SkillMapRepo;
import com.characterBuilder.services.interfaces.SkillDescriptionSrvc;
import com.characterBuilder.services.interfaces.SkillMapSrvc;
import com.characterBuilder.services.interfaces.SkillSrvc;

@Service
public class SkillMapSrvcImpl implements SkillMapSrvc{

	@Autowired
	SkillMapRepo skillMapRepo;
	
	@Autowired
	SkillSrvc skillSrvc;
	
	@Autowired
	SkillDescriptionSrvc skillDesc;
	
	@Override
	public List<SkillMap> getAllSkillMaps() {
		return skillMapRepo.findAll();
	}

	@Override
	public List<SkillMap> getSkillMapsByEvent(Event event) {
		return skillMapRepo.getByEvent(event.getId());
	}

	@Override
	public List<SkillMap> getSkillMapsByReciever(User user) {
		return skillMapRepo.getByReciever(user);
	}

	@Override
	public List<SkillMap> getSkillMapsByAttributer(User user) {
		return skillMapRepo.getByAttributer(user);
	}

	@Override
	public void addSkillMap(SkillMap skillMap) {
		skillMapRepo.save(skillMap);
		
	}

	@Override
	public void addAllSkillMaps(Collection<SkillMap> skillMaps) {
		skillMapRepo.save(skillMaps);
	}

	
}
