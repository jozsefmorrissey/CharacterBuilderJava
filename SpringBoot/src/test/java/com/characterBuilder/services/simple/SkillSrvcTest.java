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
		assert(skillSrvc.getById(1).getName().equals(skill1));
		assert(skillSrvc.getById(2).getName().equals(skill2));
	}

	@Test
	public void testAdd() {	
		int initialSize = skillSrvc.getAllSkills().size();
		
		skillSrvc.addSkill(skills.get(0));
		List<Skill> sl = skillSrvc.getAllSkills();
		Skill found = null;
		for(Skill s : sl) {
			if(s.getName().equals(skills.get(0).getName()))
				found = s;
		}
		assert(skillSrvc.getAllSkills().size() == initialSize + 1);
		assert(found != null);
		skillSrvc.removeSkill(found);
		assert(skillSrvc.getAllSkills().size() == initialSize);
	}
	
	@Test
	public void testAddExisting() {	
		int initialSize = skillSrvc.getAllSkills().size();
		
		skillSrvc.addSkill(new Skill(skill1));

		assert(skillSrvc.getAllSkills().size() == initialSize);
	}
	
	@Test 
	public void testAddAll() {
		int initialSize = skillSrvc.getAllSkills().size();
		
		skillSrvc.addAllSkills(skills);
		List<Skill> sList = skillSrvc.getAllSkills();

		verifySkillsExistInDB(sList);
		
		removeAndVerifySuccess(initialSize);
	}
	
	@Test
	public void testAddAllAddExisting() {
		List<Skill> OrigSkillsDB = skillSrvc.getAllSkills();
		int initialSize = OrigSkillsDB.size();
		
		// Generating random location to insert Already existent skill names;
		int randomLoc1 = (int)(Math.random() * skills.size());
		int randomLoc2 = (int)(Math.random() * skills.size());
		
		// Saving skill for future removal
		Skill newSkill1 = skills.get(randomLoc1);
		Skill newSkill2 = skills.get(randomLoc2);
		
		// Modifying skill names
		newSkill1.setName(skill1);
		newSkill2.setName(skill2);		
		
		skillSrvc.addAllSkills(skills);
		List<Skill> modSkillsDB = skillSrvc.getAllSkills();

		verifySkillsExistInDB(modSkillsDB);

		// Removing skills to maintain database consistency.
		skills.remove(newSkill1);
		skills.remove(newSkill2);
		
		removeAndVerifySuccess(initialSize);		
	}

	private void removeAndVerifySuccess(int initialSize) {
		assert(skillSrvc.getAllSkills().size() == initialSize + skills.size());
		skillSrvc.removeAllSkills(skills);
		assert(skillSrvc.getAllSkills().size() == initialSize);
	}

	private void verifySkillsExistInDB(List<Skill> modSkillsDB) {
		Skill found = null;
		
		for(Skill jSkill : skills) {
			for(Skill DBSkill : modSkillsDB) {
				if(DBSkill.getName().equals(jSkill.getName()))
					found = DBSkill;
			}
			assert(found != null);
		}
	}
}
