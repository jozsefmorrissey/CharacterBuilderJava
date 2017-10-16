package com.characterBuilder.srvc.impl;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.abs.RatingAbs;
import com.characterBuilder.markers.HasId;
import com.characterBuilder.markers.RatingRepoMarker;
import com.characterBuilder.srvc.interfaces.RatingSrvcGeneric;
import com.characterBuilder.throwable.exceptions.TooCloseException;
import com.characterBuilder.util.properties.CharBuildProp;

@Service
public abstract class RatingSrvcAbs<T extends RatingAbs,U extends HasId> 
		implements RatingSrvcGeneric<T, U> {
	
	public abstract RatingRepoMarker<T> getRatingRepo();
	
	@Autowired
	CharBuildProp property;
	
	@Override
	public List<T> getByReciever(U reciever) {
		return sort(getRatingRepo().findByReciever(reciever.getId()));
	}

	@Override
	public List<T> getByAttributer(User attributer) {
		return sort(getRatingRepo().findByAttributer(attributer.getId()));
	}
	

	@Override
	public List<T> getByAttributerAndReciever(User attributer, U reciever) {
		return getByAttributerAndReciever(attributer.getId(), reciever.getId());
	}
	
	private List<T> getByAttributerAndReciever(long attrId, long recId) {
		return sort(getRatingRepo().findByAttributerAndReciever(attrId, recId));
	}

	@Override
	public void addRating(T rating) throws TooCloseException {
		List<T> ratings = getByAttributerAndReciever(rating.getAttributer(), rating.getReciever());
		if(ratings.size() == 0){
			getRatingRepo().save(rating);
		} else {
	
			LocalDateTime now = LocalDateTime.now();
			rating.setDateTime(now);
			LocalDateTime mostRecent = ratings.get(0).getDateTime();
			int daysBetweenRatings = property.daysBetweenUserRatingsMin();
			LocalDateTime allowedDate = mostRecent.plusDays(daysBetweenRatings);
			if(ratings.size() == 0 || now.compareTo(allowedDate) >= 0)
				getRatingRepo().save(rating);
			else
				throw new TooCloseException("Ratings", "Days", daysBetweenRatings);
		}
	}

	@Override
	public void addAllRatings(Collection<T> ratings) throws TooCloseException {
		for(T r : ratings) {
			addRating(r);
		}
	}

	
	@Override
	public void deleteRating(T rating) {
		getRatingRepo().delete(rating);
	}
	
	@Override
	public void deleteAllRatings(Collection<T> ratings) {
		for(T rating : ratings)
			deleteRating(rating);
	}
	
	private List<T> sort(List<T> ratings) {
		Collections.sort(ratings);
		return ratings;
	}
}
