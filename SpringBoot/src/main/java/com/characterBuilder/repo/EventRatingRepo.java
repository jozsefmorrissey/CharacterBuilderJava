package com.characterBuilder.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.EventRating;
import com.characterBuilder.markers.RatingRepoMarker;

public interface EventRatingRepo 
		extends JpaRepository<EventRating, Long>, 
		RatingRepoMarker<EventRating> {

	public List<EventRating> findByReciever(long reciever);
	public List<EventRating> findByAttributer(long attributer);
	public List<EventRating> findByAttributerAndReciever(long attrId, long recId);
}
