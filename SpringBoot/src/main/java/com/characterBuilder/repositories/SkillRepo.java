package com.characterBuilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.Skill;

public interface SkillRepo extends JpaRepository<Skill, Long> {

}
