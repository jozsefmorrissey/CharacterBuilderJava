package com.characterBuilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.UserDescription;
import com.characterBuilder.markers.DescriptionRepoMarker;

public interface UserDescriptionRepo 
		extends JpaRepository<UserDescription, Long>,
			DescriptionRepoMarker<UserDescription>
{
	public void deleteByDescIdId(long id);
}
