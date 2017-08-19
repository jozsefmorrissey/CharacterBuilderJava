package com.characterBuilder.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
	public Skill getByName(String name) {
		return skillRepo.getByName(name);
	}	
	
	@Override
	@Transactional
	public Skill getById(long id) {
		return skillRepo.getOne(id);
	}

	@Override
	@Transactional
	public List<Skill> getAllSkills() {
		List<Skill> skills = skillRepo.findAll();
		Collections.sort(skills);
		return skills;
	}

	@Override
	@Transactional
	public void addSkill(Skill skill) {
		Skill found = getByName(skill.getName());
		if(found != null)
			skill.setId(found.getId());
		else
			skillRepo.save(skill);
	}

	@Override
	public void addAllSkills(Collection<Skill> skills) {
		addUniqueSkills(skills);
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
	
	private void addUniqueSkills(Collection<Skill> skillsTBA) {
		List<Skill> skillsDB = getAllSkills();
		List<Skill> unique = new ArrayList<Skill>();
		
		for(Skill s : skillsTBA) {
			int index = Collections.binarySearch(skillsDB, s);
			if (index >= 0) {
				Skill found = skillsDB.get(index);
				s.setId(found.getId());
			}
			else {
				s.setId(0);
				unique.add(s);
			}
		}
		
		skillRepo.save(unique);
	}
}
