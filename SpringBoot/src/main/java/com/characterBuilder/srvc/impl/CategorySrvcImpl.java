package com.characterBuilder.srvc.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.comparator.ComparitorId;
import com.characterBuilder.entities.Category;
import com.characterBuilder.repo.marker.CategoryRepo;
import com.characterBuilder.srvc.interfaces.CategorySrvc;

/**
 * Categories within the database should not change regularly therefore they will be stored
 * locally stored with an update function to retrieve the updated values if necessary.
 * 
 * @author jozse
 *
 */
@Service
public class CategorySrvcImpl implements CategorySrvc {
	
	@Autowired
	CategoryRepo catRepo;
	
	private List<Category> ordByName = new ArrayList<Category>();
	private List<Category> ordById = new ArrayList<Category>();

	@Override
	public Category getByName(String name) {
		getData();
		Category nameCat = new Category(0, name, "", "");
		int index = Collections.binarySearch(ordByName, nameCat);
		if(index >= 0)
			return ordByName.get(index);
		else
			return null;
	}

	@Override
	public Category getById(long id) {
		getData();
		Category idCat = new Category(id, "","", "");
		int index = Collections.binarySearch(ordById, idCat, new ComparitorId());
		if(index >= 0)
			return ordByName.get(index);
		else
			return null;
	}

	@Override
	public List<Category> getAllOrdByName() {
		getData();
		return ordByName;
	}

	@Override
	public List<Category> getAllOrdById() {
		getData();
		return ordById;
	}

	@Override
	public void updateFromDataBase() {
		ordByName = catRepo.findAll();
		ordById.addAll(ordByName);
		Collections.sort(ordByName);
		Collections.sort(ordById, new ComparitorId());
	}
	
	/**
	 * This prevents redundant calls to db and
	 * messy validation code.
	 * Since update sets both, only one check required.
	 */
	private void getData () {
		if(ordByName.size() == 0)
			updateFromDataBase();
	}
}
