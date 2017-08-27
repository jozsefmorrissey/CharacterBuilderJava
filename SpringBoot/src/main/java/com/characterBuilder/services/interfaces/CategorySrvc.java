package com.characterBuilder.services.interfaces;

import java.util.List;

import com.characterBuilder.entities.Category;

/**
 * Categories within the database should not change regularly therefore they will be stored
 * locally stored with an update function to retrieve the updated values if necessary.
 * 
 * @author jozse
 *
 */
public interface CategorySrvc {
	public Category getByName(String name);
	public Category getById(long id);
	public List<Category> getAllOrdByName();
	public List<Category> getAllOrdById();
	public void updateFromDataBase();
}
