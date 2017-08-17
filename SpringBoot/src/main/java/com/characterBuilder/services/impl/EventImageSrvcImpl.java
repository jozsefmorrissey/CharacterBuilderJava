package com.characterBuilder.services.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.EventImage;
import com.characterBuilder.repositories.EventImageRepo;
import com.characterBuilder.services.interfaces.EventImageSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.throwable.exceptions.ObjectRelationshipError;
import com.characterBuilder.util.PropertiesUtil;

/**
 * Nearly all methods in this class throw the EventImageDNMEvent exception if
 * the eventId's are not equal.
 * 
 * @author jozse
 *
 */
@Service
public class EventImageSrvcImpl implements EventImageSrvc {

	@Autowired
	EventImageRepo eventImageRepo;
	
	/**
	 * No position checks are in place for insertion. This method insures that
	 * when images are retrieved they are logically ordered by their position
	 * values and assigned a corresponding 0 based index.
	 */
	@Override
	public List<EventImage> getImagesByEvent(Event event) {
		List<EventImage> images = eventImageRepo.getByEventId(event.getId());
		Collections.sort((List<EventImage>) images);
		int position = 0;
		for(EventImage ei : images) {
			ei.setPosition(position++);
		}
		
		return images;
	}

	/**
	 * This function appends and sorts the a single image to the
	 * existing images in the database. It sorts by the position
	 * variable, if conflict emerge they are resolved arbitrarily.
	 * 
	 * @throws ObjectRelationshipError 
	 * @throws ExceedingImageCount 
	 */
	@Override
	@Transactional
	public void addImage(EventImage image) throws ExceedingLimitException {
		checkCount(image.getEventId(), 1);
		eventImageRepo.saveAndFlush(image);	
	}

	/**
	 * This function appends the new collection of images
	 * to the existing images in the database.
	 * 
	 * @throws ObjectRelationshipError 
	 * @throws ExceedingImageCount 
	 */
	@Override
	@Transactional
	public void addImages(List<EventImage> images) throws ExceedingLimitException {
		for(EventImage ei : images) {
			ei.setId(0L);
		}
		
		saveImages(images);		
	}

	/**
	 * Saves non-existing ids and updates existing ones.
	 * 
	 * @throws ObjectRelationshipError 
	 * @throws ExceedingImageCount 
	 */
	@Override
	@Transactional
	public void updateImage(EventImage image) throws ExceedingLimitException {
		long eId = image.getEventId();
		if(eventImageRepo.getByEventId(eId).size() == 0)
			checkCount(eId, 1);
		eventImageRepo.saveAndFlush(image);
	}

	/**
	 * Saves non-existing ids and updates existing ones.
	 * 
	 * @throws ObjectRelationshipError 
	 * @throws ExceedingImageCount 
	 */
	@Override
	@Transactional
	public void updateImages(List<EventImage> images) throws ExceedingLimitException {
		saveImages(images);
	}

	/**
	 * Removes all images in database and replaces it with them with
	 * the images in the given collection.
	 * 
	 * @throws ObjectRelationshipError 
	 * @throws ExceedingImageCount 
	 */
	@Override
	@Transactional
	public void updateAllImages(List<EventImage> images) throws ExceedingLimitException {
		if(images == null || images.size() < 1){
			return;
		}		
		long eId = images.get(0).getEventId();
		checkCount(eId, images.size() - eventImageRepo.countByEventId(eId));
		deleteAllImages(images.get(0).getEventId());
		saveImages(images);
	}
	
	@Override
	@Transactional
	public void deleteImage(EventImage image) {
		eventImageRepo.delete(image);
	}

	@Override
	@Transactional
	public void deleteImages(List<EventImage> images) {
		eventImageRepo.delete(images);
	}
	
	@Override
	@Transactional
	public void deleteAllImages(long eventId) {
		eventImageRepo.deleteByEventId(eventId);
	}
	

	private void saveImages(List<EventImage> images) throws ExceedingLimitException {
		if(images == null || images.size() < 1){
			return;
		}
		int duplacates = countDuplacateImageIdsInDb(images.get(0).getEventId(), images);
		checkCount(images.get(0).getEventId(), -1 * duplacates + images.size());
		eventImageRepo.save(images);
	}
	
	private void checkCount(long eventId, int addCount) throws ExceedingLimitException {
		int MaxImageCount = PropertiesUtil.getMaxImageCount();
		int currentCount = addCount + eventImageRepo.countByEventId(eventId);
		if(MaxImageCount < currentCount) {
			//TODO: If exception does not roll back transaction clean up the database.
			throw new ExceedingLimitException(currentCount, MaxImageCount, "Event Images");
		}
	}
	
	private int countDuplacateImageIdsInDb(long eventId, Collection<EventImage> images) {
		List<EventImage> searchSet = eventImageRepo.getByEventId(eventId);
		searchSet.addAll(images);
		Collections.sort(searchSet, new EventImage());
		int duplacateCount = 0;
		for(int index = 0; index < searchSet.size() - 1; index++) {
			if(searchSet.get(index).getId() != 0 && searchSet.get(index).getId()== searchSet.get(index + 1).getId())
				duplacateCount++;
		}
		return duplacateCount;
	}
}
