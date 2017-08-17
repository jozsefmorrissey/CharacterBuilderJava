package com.characterBuilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.SkillDescription;

public interface SkillDescriptionRepo extends JpaRepository<SkillDescription, Long> {
	public void deleteBySkillMapId(long id);
}
