package com.characterBuilder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.characterBuilder.entities.pureDBEntities.Description;

public interface DescriptionRepo extends JpaRepository<Description, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM EVENT_DESCRIPTION_TBL")
	public List<Description> getAllEventDesc();

	@Query(nativeQuery = true, value = "SELECT * FROM EVENT_DESCRIPTION_TBL WHERE ID = :id")
	public List<Description> getEventDesc(@Param(value = "id") long id);
	
	@Query(nativeQuery = true, value = "SELECT * FROM SKILL_DESCRIPTION_TBL")
	public List<Description> getAllSkillDesc();

	@Query(nativeQuery = true, value = "SELECT * FROM SKILL_DESCRIPTION_TBL WHERE ID = :id")
	public List<Description> getSkillDesc(@Param(value = "id") long id);

	@Query(nativeQuery = true, value = "SELECT * FROM USER_DESCRIPTION_TBL")
	public List<Description> getAllUserDesc();

	@Query(nativeQuery = true, value = "SELECT * FROM USER_DESCRIPTION_TBL WHERE ID = :id")
	public List<Description> getUserDesc(long id);
}
