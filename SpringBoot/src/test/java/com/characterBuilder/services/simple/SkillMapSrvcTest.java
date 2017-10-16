package com.characterBuilder.services.simple;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.SkillMap;
import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.Skill;
import com.characterBuilder.srvc.impl.ConstantSrvcAbs;
import com.characterBuilder.srvc.interfaces.SkillMapSrvc;
import com.characterBuilder.srvc.interfaces.UserSrvc;

//TODO: test add and remove functions
@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillMapSrvcTest {
	
	@Autowired
	SkillMapSrvc skillMapSrvc;
	
	@Autowired
	ConstantSrvcAbs<Skill> skillSrvc;
	
	@Autowired
	UserSrvc userSrvc;
	
	int[] skIDs = new int[]{1,2,1};
	int[] evIDs = new int[]{1,1,2};
	int[] atIDs = new int[]{2,2,1};
	int[] rcIDs = new int[]{1,1,2};
	short[] vals = new short[]{9,9,2};
	short[] atVal = new short[]{8,7,9};
	
	private List<SkillMap> at1 = new ArrayList<SkillMap>();
	private List<SkillMap> rc1 = new ArrayList<SkillMap>();
	
	private User user;
	
	@Before
	public void initialize() {
		user = userSrvc.getById(1);
		for(int i = 0; i < skIDs.length; i++) {
			Skill sk = skillSrvc.getById(skIDs[i]);
			User at = userSrvc.getById(atIDs[i]);
			User rc = userSrvc.getById(rcIDs[i]);
			
			SkillMap skillMap = new SkillMap(i + 1, sk, evIDs[i], at, rc, vals[i], atVal[i],null);
			if(at.getId() == 1)
				at1.add(skillMap);
			if(rc.getId() == 1)
				rc1.add(skillMap);
		}
	}
	
	@Test
	public void testGetByAttr() {
		List<SkillMap> dbMaps = skillMapSrvc.getSkillMapsByAttributer(user);
		verifyEqual(dbMaps, at1);
	}
	
	@Test
	public void testGetByRec() {
		List<SkillMap> dbMaps = skillMapSrvc.getSkillMapsByReciever(user);
		verifyEqual(dbMaps, rc1);
	}

	private void verifyEqual(List<SkillMap> dbMaps, List<SkillMap> maps) {
		for(SkillMap sm : maps) {
			int count = 0;
			for(SkillMap dbSm : dbMaps) {
				if(sm.equals(dbSm)) {
					count++;
				}
			}
			assert(count == 1);
		}
	}
}