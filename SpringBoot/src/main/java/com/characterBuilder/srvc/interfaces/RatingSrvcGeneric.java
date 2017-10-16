package com.characterBuilder.srvc.interfaces;

import java.util.Collection;
import java.util.List;

import com.characterBuilder.entities.User;
import com.characterBuilder.markers.HasId;
import com.characterBuilder.throwable.exceptions.TooCloseException;

public interface RatingSrvcGeneric<T, U extends HasId>
{
	public List<T> getByReciever(U reciever);
	public List<T> getByAttributer(User attributer);
	public List<T> getByAttributerAndReciever(User attributer, U reciever);
	
	public void addRating(T rating) throws TooCloseException;
	void addAllRatings(Collection<T> ratings) throws TooCloseException;
	
	public void deleteRating(T rating);
	void deleteAllRatings(Collection<T> ratings);
}
