package com.characterBuilder.services.simple;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.Category;
import com.characterBuilder.srvc.interfaces.CategorySrvc;


/*
 * TODO: Finish Testing...
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategorySrvcTest {

	@Autowired
	private CategorySrvc catSrvc;
	
	String[] catNames = new String[]{"Volunteer", "Charity", "Educational", 
			"Trade", "Social", "Active", "Distress", "Siminar"};
	
	private List<Category> categories = new ArrayList<Category>();
	
	@Before
	public void intialize() {
		for(int i = 0; i < catNames.length; i++) {
			categories.add(new Category(i + 1, catNames[i], "", ""));
		}
	}
	
	@Test
	public void testGetAllName() {
		List<Category> cats = catSrvc.getAllOrdByName();
		
		//Ensure order
		Category prev = null;
		for(Category c : cats) {
			if(prev != null)
				assert(prev.getName().compareTo(c.getName()) <= 0);
		}
		
		verifyEqual(cats);
	}

	@Test
	public void testGetAllId() {
		List<Category> cats = catSrvc.getAllOrdById();
		
		//Ensure order
		Category prev = null;
		for(Category c : cats) {
			if(prev != null)
				assert(prev.getId() < c.getId());
		}
		
		verifyEqual(cats);
	}
	
	private void verifyEqual(List<Category> cats) {
		for (Category c1 : cats) {
			boolean found = false;
			for(Category c2 : categories) {
				if(verifyEqual(c1, c2)) {
					found = true;
				}
			}
			assert(found);
		}
	}
	
	private boolean verifyEqual(Category cat1, Category cat2) {
		return cat1.getId() == cat2.getId() && cat1.getName().equals(cat2.getName());
	}
}
