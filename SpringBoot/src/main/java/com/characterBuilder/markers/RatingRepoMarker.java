package com.characterBuilder.markers;

import java.util.List;

import com.characterBuilder.entities.abs.RatingAbs;

public interface RatingRepoMarker<T extends RatingAbs>
{
	public List<T> findByReciever(long reciever);
	public List<T> findByAttributer(long attributer);
	public List<T> findByAttributerAndReciever(long attrId, long recId);
	public T save(T rating);
	public void delete(T rating);
}
