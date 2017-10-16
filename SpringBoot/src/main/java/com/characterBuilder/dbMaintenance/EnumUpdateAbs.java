package com.characterBuilder.dbMaintenance;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.characterBuilder.entities.abs.ConstantAbs;
import com.characterBuilder.srvc.impl.ConstantSrvcAbs;

public abstract class EnumUpdateAbs
		<E extends Enum<E>, U extends ConstantAbs>
{
	Logger log = Logger.getRootLogger();
	
	@Autowired
	ConstantSrvcAbs<U> constSrvc;
	
	protected abstract Class<E> getEnum();
	
	public void update() {
		addNewProperties();
		notTracked();
	}
	
	private void addNewProperties()
	{
		E[] enumValues = getEnum().getEnumConstants();
		List<U> dbValues = constSrvc.getAll();
		
		for(E enumVal : enumValues) {
			boolean found = false;
			for(U dbVal : dbValues) {
				String dbStr = dbVal.getValue();
				if(dbStr != null && dbStr.equals(enumVal.toString())) {
					found = true;
					break;
				}
			}
			if(!found) {
				addToDb(dbValues, enumVal);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private void addToDb(List<U> dbValues, E enumVal)
	{
		U newConst = null;
		try
		{
			newConst = (U) dbValues.get(0).getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e)
		{
			e.printStackTrace();
		}
		newConst.setValue(enumVal.toString());
		constSrvc.add(newConst);
	}
	
	private void notTracked() {
		E[] enumValues = getEnum().getEnumConstants();
		List<U> dbValues = constSrvc.getAll();
		
		for(U dbVal : dbValues) {
			boolean found = false;
			for(E enumVal : enumValues) {
				String dbStr = dbVal.getValue();
				if(dbStr != null && dbStr.equals(enumVal.toString())) {
					found = true;
					break;
				}
			}
			if(!found) {
				log.error("not tracked: " + dbVal.getValue());
			}
		}
	}
}
