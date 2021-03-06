package com.characterBuilder.srvc.interfaces;

import java.util.List;

import com.characterBuilder.entities.pureDBEntities.Description;

public interface DescriptionSrvc {
	public String getEventDescription(long id);
	public List<Description> getAllEventDesc();
	public String getSkillDescription(long id);
	public List<Description> getAllSkillDesc();
	public List<Description> getAllUserDesc();
	public String getUserDescription(long id);
}
