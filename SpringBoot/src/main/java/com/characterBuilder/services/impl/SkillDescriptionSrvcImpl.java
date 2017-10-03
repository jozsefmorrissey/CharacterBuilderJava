package com.characterBuilder.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.SkillMap;
import com.characterBuilder.entities.pureDBEntities.Description;
import com.characterBuilder.entities.pureDBEntities.SkillDescription;
import com.characterBuilder.repositories.SkillDescriptionRepo;
import com.characterBuilder.services.interfaces.DescriptionSrvc;
import com.characterBuilder.services.interfaces.SkillDescriptionSrvc;
import com.characterBuilder.throwable.exceptions.InputTooLong;
import com.characterBuilder.throwable.exceptions.OverwritingDataException;
import com.characterBuilder.util.PropertiesUtil;
import com.characterBuilder.util.StringUtil;

@Service
public class SkillDescriptionSrvcImpl implements SkillDescriptionSrvc {

	@Autowired
	SkillDescriptionRepo skillDescRepo;
	
	@Autowired
	DescriptionSrvc descSrvc;
	
	@Override
	public void add(SkillMap skillMap, String description) throws OverwritingDataException, InputTooLong {
		long id = skillMap.getId();

		// Varify that no Description exists if a null value exists or the index is out
		// of bounds I force these two cases into the catch block which is the only place
		// additions to the database are made.
		try{
		  String ed = descSrvc.getSkillDescription(id);
		  if(ed == null)
		    throw new IndexOutOfBoundsException();
		  
		  throw new OverwritingDataException();
		}catch (IndexOutOfBoundsException e){
		  this.divideAndAddData(skillMap, description);
		}
	}

	@Override
	@Transactional
	public void delete(SkillMap skillMap) {
		skillDescRepo.deleteBySkillMapId(skillMap.getId());
	}

	@Override
	@Transactional
	public void update(SkillMap skillMap, String description) throws OverwritingDataException, InputTooLong {
		delete(skillMap);
		add(skillMap, description);
	}

	@Override
	public List<Description> getAllSkillDescriptions() {
		return descSrvc.getAllSkillDesc();
	}

	@Override
	public void setDescription(SkillMap skillMap) {
		String desc = descSrvc.getSkillDescription(skillMap.getId());
		skillMap.setDescription(desc);
	}
	
	/**
	 * 	// This function divides the description string into 248 char strings. The size in the database
	// Is 256 but testing yielded that 248 was the largest string they would accept. The position
	// attribute is used to properly reassemble these strings when retrieved.
	 * 
	 * @param event - event connected to description
	 * @param description - the discription of the event
	 * @throws InputTooLong - Exception thrown if description exceeds descriptionLength that is set
	 * 						  in charBuild.properties.
	 */
	@Transactional
	private void divideAndAddData(SkillMap skillMap, String description) throws InputTooLong {
		if(description.length() > PropertiesUtil.descriptionLength())
			throw new InputTooLong(description.length(), PropertiesUtil.descriptionLength());
		String[] strs = StringUtil.uniformLengthStrings(description, PropertiesUtil.descriptionSegmentLength());
		ArrayList<SkillDescription> skillDescs = new ArrayList<SkillDescription>();
		for(int index = 0; index < strs.length; index++) {
			skillDescs.add(new SkillDescription(0, skillMap.getId(), strs[index], index));
		}
		skillDescRepo.save(skillDescs);
	}

}