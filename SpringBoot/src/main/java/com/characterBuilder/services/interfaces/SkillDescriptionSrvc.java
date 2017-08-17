package com.characterBuilder.services.interfaces;

import java.util.List;

import com.characterBuilder.entities.SkillMap;
import com.characterBuilder.entities.pureDBEntities.Description;
import com.characterBuilder.throwable.exceptions.InputTooLong;
import com.characterBuilder.throwable.exceptions.OverwritingDataException;

public interface SkillDescriptionSrvc {
	public void add(SkillMap skillMap, String description) throws OverwritingDataException, InputTooLong;
	public void delete(SkillMap skillMap);
	public void update(SkillMap skillMap, String description) throws OverwritingDataException, InputTooLong;
	public List<Description> getAllSkillDescriptions();
	public void setDescription(SkillMap skillMap);
}
