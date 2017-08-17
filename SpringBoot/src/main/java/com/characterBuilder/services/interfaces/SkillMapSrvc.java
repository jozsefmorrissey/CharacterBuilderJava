package com.characterBuilder.services.interfaces;

import java.util.Collection;
import java.util.List;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.SkillMap;
import com.characterBuilder.entities.User;

public interface SkillMapSrvc {
	public List<SkillMap> getAllSkillMaps();
//	public List<SkillMap> getSkillMapsByEvent(Event event);
//	public List<SkillMap> getSkillMapsByReciever(User user);
//	public List<SkillMap> getSkillMapsByAttributer(User user);

	public void addSkillMap(SkillMap skillMap);
	public void addAllSkillMaps(Collection<SkillMap> skillMaps);	
}
