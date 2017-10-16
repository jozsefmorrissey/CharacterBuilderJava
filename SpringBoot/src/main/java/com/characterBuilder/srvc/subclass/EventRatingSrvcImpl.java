package com.characterBuilder.srvc.subclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.EventRating;
import com.characterBuilder.markers.RatingRepoMarker;
import com.characterBuilder.repo.EventRatingRepo;
import com.characterBuilder.srvc.impl.RatingSrvcAbs;
import com.characterBuilder.srvc.interfaces.EventRatingSrvc;

@Service
public class EventRatingSrvcImpl 
		extends RatingSrvcAbs<EventRating, Event> 
		implements EventRatingSrvc {

	@Autowired
	EventRatingRepo ratingRepo;

	@Override
	public RatingRepoMarker<EventRating> getRatingRepo()
	{
		return ratingRepo;
	}
}
