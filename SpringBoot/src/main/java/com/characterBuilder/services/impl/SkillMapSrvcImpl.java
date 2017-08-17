package com.characterBuilder.services.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.SkillMap;
import com.characterBuilder.entities.User;
import com.characterBuilder.repositories.SkillMapRepo;
import com.characterBuilder.services.interfaces.SkillMapSrvc;

@Service
public class SkillMapSrvcImpl implements SkillMapSrvc{

	@Autowired
	SkillMapRepo skillMapRepo;
	
	@Override
	public List<SkillMap> getAllSkillMaps() {
		return skillMapRepo.findAll();
	}

//	@Override
//	public List<SkillMap> getSkillMapsByEvent(Event event) {
//		return skillMapRepo.getByEventId(event.getId());
//	}
//
//	@Override
//	public List<SkillMap> getSkillMapsByReciever(User user) {
//		return skillMapRepo.getByReciever(user);
//	}
//
//	@Override
//	public List<SkillMap> getSkillMapsByAttributer(User user) {
//		return skillMapRepo.getByAttributer(user);
//	}

	@Override
	public void addSkillMap(SkillMap skillMap) {
		skillMapRepo.save(skillMap);
		
	}

	@Override
	public void addAllSkillMaps(Collection<SkillMap> skillMaps) {
		skillMapRepo.save(skillMaps);
	}

	
}
