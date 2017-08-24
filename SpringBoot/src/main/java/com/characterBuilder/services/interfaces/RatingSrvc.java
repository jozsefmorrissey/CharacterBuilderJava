package com.characterBuilder.services.interfaces;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.Rating;
import com.characterBuilder.throwable.exceptions.TooCloseException;

@Service
public interface RatingSrvc {
	public List<Rating> getByReciever(User reciever);
	public List<Rating> getByAttributer(User attributer);
	public List<Rating> getByAttributerAndReciever(User attributer, User reciever);
	
	public void addRating(Rating rating) throws TooCloseException;
	void addAllRatings(Collection<Rating> ratings) throws TooCloseException;
	
	public void deleteRating(Rating rating);
	void deleteAllRatings(Collection<Rating> ratings);
}
