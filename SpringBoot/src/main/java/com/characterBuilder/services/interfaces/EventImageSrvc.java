package com.characterBuilder.services.interfaces;

import java.util.List;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.EventImage;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;

public interface EventImageSrvc {
	public List<EventImage> getImagesByEvent(Event event);
	public void addImage(EventImage image) throws ExceedingLimitException;
	public void addImages(List<EventImage> images) throws ExceedingLimitException;
	public void updateImage(EventImage image) throws ExceedingLimitException;
	public void updateImages(List<EventImage> image) throws ExceedingLimitException;
	public void updateAllImages(List<EventImage> images) throws ExceedingLimitException;
	public void deleteImage(EventImage image);
	public void deleteImages(List<EventImage> images);
	void deleteAllImages(long eventId);
}
