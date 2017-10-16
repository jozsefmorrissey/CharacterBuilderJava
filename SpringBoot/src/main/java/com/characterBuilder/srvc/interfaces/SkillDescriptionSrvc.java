package com.characterBuilder.srvc.interfaces;

import java.util.List;

import com.characterBuilder.entities.SkillMap;
import com.characterBuilder.entities.pureDBEntities.Description;
import com.characterBuilder.entities.pureDBEntities.SkillDescription;
import com.characterBuilder.throwable.exceptions.DependenciesNotFullfilledException;
import com.characterBuilder.throwable.exceptions.InputTooLong;
import com.characterBuilder.throwable.exceptions.OverwritingDataException;

public interface SkillDescriptionSrvc extends DescriptionSrvcGeneric<SkillDescription, SkillMap> {
	public void add(SkillMap skillMap, String description) throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException;
	public void delete(SkillMap skillMap);
	public void update(SkillMap skillMap, String description) throws OverwritingDataException, InputTooLong, DependenciesNotFullfilledException;
	public List<Description> getAllDescriptions();
	public void setDescription(SkillMap skillMap);
}
