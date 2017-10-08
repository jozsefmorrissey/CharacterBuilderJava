package com.characterBuilder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.ProfileLink;

public interface ProfileLinkRepo extends JpaRepository<ProfileLink, Long> {

	public List<ProfileLink> findByIdCreator(User user);
	
	@Query(nativeQuery = true, value = "SELECT * FROM TABLE(FIND_PROFILE_LINKS(:id))")
	public List<ProfileLink> findNatural(@Param(value = "id") long id);
	
//	public List<ProfileLink> findByUser1OrUser2(User user1, User user2);
}
