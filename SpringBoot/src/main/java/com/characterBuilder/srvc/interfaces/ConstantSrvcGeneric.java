package com.characterBuilder.srvc.interfaces;

import java.util.Collection;
import java.util.List;

import com.characterBuilder.entities.abs.ConstantAbs;

public interface ConstantSrvcGeneric
		<T extends ConstantAbs>
{
	public List<T> getAll();
	public T getByValue(String name);
	public T getById(long id);
	
	public void add(T constant);
	
	public void addAll(Collection<T> constants);
	
	public void remove(T constant);
	public void removeAll(Collection<T> constants);
}
