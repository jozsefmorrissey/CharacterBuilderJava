package com.characterBuilder.services.interfaces;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.UserRating;
import com.characterBuilder.throwable.exceptions.TooCloseException;

public interface RatingSrvc {
	public List<UserRating> getByReciever(User reciever);
	public List<UserRating> getByAttributer(User attributer);
	public List<UserRating> getByAttributerAndReciever(User attributer, User reciever);
	
	public void addRating(UserRating rating) throws TooCloseException;
	void addAllRatings(Collection<UserRating> ratings) throws TooCloseException;
	
	public void deleteRating(UserRating rating);
	void deleteAllRatings(Collection<UserRating> ratings);
}
