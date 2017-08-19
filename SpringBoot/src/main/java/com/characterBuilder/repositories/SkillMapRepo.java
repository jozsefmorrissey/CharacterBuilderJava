package com.characterBuilder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.SkillMap;
import com.characterBuilder.entities.User;

public interface SkillMapRepo extends JpaRepository<SkillMap, Long> {	
	List<SkillMap> getByEvent(long id);
	List<SkillMap> getByReciever(User user);
	List<SkillMap> getByAttributer(User user);
}
