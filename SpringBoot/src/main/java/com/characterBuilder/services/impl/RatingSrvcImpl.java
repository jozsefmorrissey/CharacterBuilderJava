package com.characterBuilder.services.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.Rating;
import com.characterBuilder.repositories.RatingRepo;
import com.characterBuilder.services.interfaces.RatingSrvc;
import com.characterBuilder.throwable.exceptions.TooCloseException;
import com.characterBuilder.util.PropertiesUtil;

@Service
public class RatingSrvcImpl implements RatingSrvc {

	@Autowired
	RatingRepo ratingRepo;
	
	@Override
	public List<Rating> getByReciever(User reciever) {
		return sort(ratingRepo.findByReciever(reciever));
	}

	@Override
	public List<Rating> getByAttributer(User attributer) {
		return sort(ratingRepo.findByAttributer(attributer));
	}
	

	@Override
	public List<Rating> getByAttributerAndReciever(User attributer, User reciever) {
		return sort(ratingRepo.findByAttributerAndReciever(attributer, reciever));
	}

	@Override
	public void addRating(Rating rating) throws TooCloseException {
		List<Rating> ratings = getByAttributerAndReciever(rating.getAttributer(), rating.getReciever());
		if(ratings.size() == 0){
			ratingRepo.save(rating);
		} else {
	
			LocalDateTime now = LocalDateTime.now();
			rating.setDateTime(now);
			LocalDateTime mostRecent = ratings.get(0).getDateTime();
			int daysBetweenRatings = PropertiesUtil.daysBetweenUserRatingsMin();
			LocalDateTime allowedDate = mostRecent.plusDays(daysBetweenRatings);
			if(ratings.size() == 0 || now.compareTo(allowedDate) >= 0)
				ratingRepo.save(rating);
			else
				throw new TooCloseException("Ratings", "Days", daysBetweenRatings);
		}
	}

	@Override
	public void addAllRatings(Collection<Rating> ratings) throws TooCloseException {
		for(Rating r : ratings) {
			addRating(r);
		}
	}

	
	@Override
	public void deleteRating(Rating rating) {
		ratingRepo.delete(rating);
	}
	
	@Override
	public void deleteAllRatings(Collection<Rating> ratings) {
		ratingRepo.delete(ratings);
	}
	
	private List<Rating> sort(List<Rating> ratings) {
		Collections.sort(ratings);
		return ratings;
	}
}
