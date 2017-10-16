package com.characterBuilder.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.UserRating;
import com.characterBuilder.markers.RatingRepoMarker;

public interface UserRatingRepo 
		extends JpaRepository<UserRating, Long>, 
		RatingRepoMarker<UserRating> {

	public List<UserRating> findByReciever(long reciever);
	public List<UserRating> findByAttributer(long attributer);
	public List<UserRating> findByAttributerAndReciever(long attrId, long recId);
}
