package com.characterBuilder.services.interfaces;

import java.util.Collection;
import java.util.List;

import com.characterBuilder.entities.pureDBEntities.Skill;

public interface SkillSrvc {
	public Skill getByName(String name);
	public Skill getById(long id);
	public List<Skill> getAllSkills();
	
	public void addSkill(Skill skill);
	public void addAllSkills(Collection<Skill> skills);
	
	public void removeSkill(Skill skill);
	public void removeAllSkills(Collection<Skill> skills);
}
