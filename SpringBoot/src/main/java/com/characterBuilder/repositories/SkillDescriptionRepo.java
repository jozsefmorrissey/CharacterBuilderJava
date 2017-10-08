package com.characterBuilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.SkillDescription;
import com.characterBuilder.markers.DescriptionRepoMarker;

public interface SkillDescriptionRepo 
		extends JpaRepository<SkillDescription, Long>, DescriptionRepoMarker<SkillDescription> {
		
	public void deleteByDescIdId(long id);
}
