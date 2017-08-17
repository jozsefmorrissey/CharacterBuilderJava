package com.characterBuilder.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.pureDBEntities.Skill;
import com.characterBuilder.services.interfaces.SkillSrvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillSrvcTest {
	
	@Autowired
	SkillSrvc skillSrvc;
	
	String skill1 = "Hard Working";
	String skill2 = "ANALITICAL";
	ArrayList<Skill> skills = new ArrayList<Skill>();
	
	@Before
	public void initialize() {
		String[] skills = new String[]{"Problem Solving", "Adaptability", "Collaboration", 
				"Strong Work Ethic", "Time Management", "Critical Thinking", "Self-Confidence", 
				"Handling Pressure"};
		
		for(int index = 0; index < skills.length; index++) {
			this.skills.add(new Skill(skills[index]));
		}
		
	}
	
	@Test
	public void testGet() {
		assert(skillSrvc.getAllSkills().size() == 2);
		assert(skillSrvc.getById(1).getName().equals(skill1));
		assert(skillSrvc.getById(2).getName().equals(skill2));
	}

	@Test
	public void testAdd() {	
		assert(skillSrvc.getAllSkills().size() == 2);
		skillSrvc.addSkill(skills.get(0));
		List<Skill> sl = skillSrvc.getAllSkills();
		Skill found = null;
		for(Skill s : sl) {
			if(s.getName().equals(skills.get(0).getName()))
				found = s;
		}
		assert(skillSrvc.getAllSkills().size() == 3);
		assert(found != null);
		skillSrvc.removeSkill(found);
		assert(skillSrvc.getAllSkills().size() == 2);
	}
	
	@Test 
	public void testAddAll() {
		assert(skillSrvc.getAllSkills().size() == 2);
		skillSrvc.addAllSkills(skills);
		List<Skill> sList = skillSrvc.getAllSkills();
		Skill found = null;
		
		for(Skill jSkill : skills) {
			for(Skill DBSkill : sList) {
				if(DBSkill.getName().equals(jSkill.getName()))
					found = DBSkill;
			}
			assert(found != null);
		}
		
		assert(skillSrvc.getAllSkills().size() == 2 + skills.size());
		skillSrvc.removeAllSkills(skills);
		assert(skillSrvc.getAllSkills().size() == 2);
	}
}
