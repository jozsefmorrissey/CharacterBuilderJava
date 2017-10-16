package com.characterBuilder.services.simple;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.pureDBEntities.Skill;
import com.characterBuilder.srvc.impl.ConstantSrvcAbs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillSrvcTest {
	
	@Autowired
	ConstantSrvcAbs<Skill> skillSrvc;
	
	String skill1 = "Hard Working";
	String skill2 = "ANALITICAL";
	ArrayList<Skill> skills = new ArrayList<Skill>();
	
	@Before
	public void initialize() {
		String[] skills = new String[]{"Problem Solving", "Adaptability", "Collaboration", 
				"Strong Work Ethic", "Time Management", "Critical Thinking", "Self-Confidence", 
				"Handling Pressure"};
		
		for(int index = 0; index < skills.length; index++) {
			this.skills.add(new Skill(index + 1000, skills[index]));
		}
		
	}
	
	@Test
	public void testGet() {
		assert(skillSrvc.getById(1).getValue().equals(skill1));
		assert(skillSrvc.getById(2).getValue().equals(skill2));
	}

	@Test
	public void testAdd() {	
		int initialSize = skillSrvc.getAll().size();
		
		skillSrvc.add(skills.get(0));
		List<Skill> sl = skillSrvc.getAll();
		Skill found = null;
		for(Skill s : sl) {
			if(s.getValue().equals(skills.get(0).getValue()))
				found = s;
		}
		assert(skillSrvc.getAll().size() == initialSize + 1);
		assert(found != null);
		skillSrvc.remove(found);
		assert(skillSrvc.getAll().size() == initialSize);
	}
	
	@Test
	public void testAddExisting() {	
		int initialSize = skillSrvc.getAll().size();
		
		skillSrvc.add(new Skill(1l, skill1));

		assert(skillSrvc.getAll().size() == initialSize);
	}
	
	@Test 
	public void testAddAll() {
		int initialSize = skillSrvc.getAll().size();
		
		skillSrvc.addAll(skills);
		List<Skill> sList = skillSrvc.getAll();

		verifySkillsExistInDB(sList);
		
		removeAndVerifySuccess(initialSize);
	}
	
	@Test
	public void testAddAllAddExisting() {
		List<Skill> OrigSkillsDB = skillSrvc.getAll();
		int initialSize = OrigSkillsDB.size();
		
		// Generating random location to insert Already existent skill names;
		int randomLoc1 = (int)(Math.random() * skills.size());
		int randomLoc2 = (int)(Math.random() * skills.size());
		
		// Saving skill for future removal
		Skill newSkill1 = skills.get(randomLoc1);
		Skill newSkill2 = skills.get(randomLoc2);
		
		// Modifying skill names
		newSkill1.setValue(skill1);
		newSkill2.setValue(skill2);		
		
		skillSrvc.addAll(skills);
		List<Skill> modSkillsDB = skillSrvc.getAll();

		verifySkillsExistInDB(modSkillsDB);

		// Removing skills to maintain database consistency.
		skills.remove(newSkill1);
		skills.remove(newSkill2);
		
		removeAndVerifySuccess(initialSize);		
	}

	private void removeAndVerifySuccess(int initialSize) {
		assert(skillSrvc.getAll().size() == initialSize + skills.size());
		skillSrvc.removeAll(skills);
		assert(skillSrvc.getAll().size() == initialSize);
	}

	private void verifySkillsExistInDB(List<Skill> modSkillsDB) {
		Skill found = null;
		
		for(Skill jSkill : skills) {
			for(Skill DBSkill : modSkillsDB) {
				if(DBSkill.getValue().equals(jSkill.getValue()))
					found = DBSkill;
			}
			assert(found != null);
		}
	}
}
