package com.characterBuilder.srvc.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.SkillMap;
import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.Skill;
import com.characterBuilder.markers.ConstantRepo;
import com.characterBuilder.repo.SkillMapRepo;
import com.characterBuilder.srvc.interfaces.SkillDescriptionSrvc;
import com.characterBuilder.srvc.interfaces.SkillMapSrvc;

@Service
public class SkillMapSrvcImpl implements SkillMapSrvc{

	@Autowired
	SkillMapRepo skillMapRepo;
	
	@Autowired
	ConstantRepo<Skill> skillSrvc;
	
	@Autowired
	SkillDescriptionSrvc skillDesc;
	
	@Override
	@Transactional
	public List<SkillMap> getAllSkillMaps() {
		return skillMapRepo.findAll();
	}

	@Override
	@Transactional
	public List<SkillMap> getSkillMapsByEvent(Event event) {
		return skillMapRepo.getByEventId(event.getId());
	}

	@Override
	@Transactional
	public List<SkillMap> getSkillMapsByReciever(User user) {
		return skillMapRepo.getByReciever(user);
	}

	@Override
	@Transactional
	public List<SkillMap> getSkillMapsByAttributer(User user) {
		return skillMapRepo.getByAttributer(user);
	}

	@Override
	@Transactional
	public void addSkillMap(SkillMap skillMap) {
		skillMapRepo.save(skillMap);
		
	}

	@Override
	@Transactional
	public void addAllSkillMaps(Collection<SkillMap> skillMaps) {
		skillMapRepo.save(skillMaps);
	}

	
}
