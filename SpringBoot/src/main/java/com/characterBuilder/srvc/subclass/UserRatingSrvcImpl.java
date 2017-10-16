package com.characterBuilder.srvc.subclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.UserRating;
import com.characterBuilder.markers.RatingRepoMarker;
import com.characterBuilder.repo.UserRatingRepo;
import com.characterBuilder.srvc.impl.RatingSrvcAbs;
import com.characterBuilder.srvc.interfaces.UserRatingSrvc;

@Service
public class UserRatingSrvcImpl 
		extends RatingSrvcAbs<UserRating, User> 
		implements UserRatingSrvc {

	@Autowired
	UserRatingRepo ratingRepo;

	@Override
	public RatingRepoMarker<UserRating> getRatingRepo()
	{
		return ratingRepo;
	}
}
