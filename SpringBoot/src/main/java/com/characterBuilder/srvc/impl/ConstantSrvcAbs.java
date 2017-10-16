package com.characterBuilder.srvc.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.abs.ConstantAbs;
import com.characterBuilder.markers.ConstantRepo;
import com.characterBuilder.srvc.interfaces.ConstantSrvcGeneric;

public class ConstantSrvcAbs
	<T extends ConstantAbs>
	implements ConstantSrvcGeneric<T>
{
	@Autowired
	ConstantRepo<T> constRepo;
	
	public ConstantRepo<T> getConstantRepo() {
		return constRepo; 
	}
	
	@Override
	public T getByValue(String name) {
		return getConstantRepo().getByValue(name);
	}	
	
	@Override
	@Transactional
	public T getById(long id) {
		return getConstantRepo().getOne(id);
	}

	@Override
	@Transactional
	public List<T> getAll() {
		List<T> constants = getConstantRepo().findAll();
		Collections.sort(constants);
		return constants;
	}

	@Override
	@Transactional
	public void add(T constant) {
		T found = getByValue(constant.getValue());
		if(found != null)
			constant.setId(found.getId());
		else
			getConstantRepo().save(constant);
	}

	@Override
	public void addAll(Collection<T> constants) {
		addUnique(constants);
	}

	@Override
	@Transactional
	public void remove(T constant) {
		getConstantRepo().delete(constant);		
	}

	@Override
	@Transactional
	public void removeAll(Collection<T> constants) {
		getConstantRepo().delete(constants);
	}
	
	private void addUnique(Collection<T> constantsTBA) {
		List<T> constantsDB = getAll();
		List<T> unique = new ArrayList<T>();
		
		for(T s : constantsTBA) {
			int index = Collections.binarySearch(constantsDB, s);
			if (index >= 0) {
				T found = constantsDB.get(index);
				s.setId(found.getId());
			}
			else {
				unique.add(s);
			}
		}
		
		getConstantRepo().save(unique);
	}
}
