package com.characterBuilder.services.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.pureDBEntities.Skill;
import com.characterBuilder.repositories.SkillRepo;
import com.characterBuilder.services.interfaces.SkillSrvc;

@Service
public class SkillSrvcImpl implements SkillSrvc {

	@Autowired
	SkillRepo skillRepo;
	
	@Override
	@Transactional
	public Skill getById(long id) {
		return skillRepo.getOne(id);
	}

	@Override
	@Transactional
	public List<Skill> getAllSkills() {
		return skillRepo.findAll();
	}

	@Override
	@Transactional
	public void addSkill(Skill skill) {
		skillRepo.save(skill);
	}

	@Override
	public void addAllSkills(Collection<Skill> skills) {
		skillRepo.save(skills);
	}

	@Override
	@Transactional
	public void removeSkill(Skill skill) {
		skillRepo.delete(skill);		
	}

	@Override
	@Transactional
	public void removeAllSkills(Collection<Skill> skills) {
		skillRepo.delete(skills);
	}	
}
