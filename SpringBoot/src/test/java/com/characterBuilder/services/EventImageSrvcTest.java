package com.characterBuilder.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.characterBuilder.entities.Event;
import com.characterBuilder.entities.pureDBEntities.EventImage;
import com.characterBuilder.services.interfaces.EventImageSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.util.ImageUtils;
import com.characterBuilder.util.PropertiesUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventImageSrvcTest {
	
	@Autowired
	private EventImageSrvc eventImageSrvc;
	
	private Event event;
	private EventImage image;
	private String testFileTemplate = "src/main/resources/static/images/testImages/testImage-?.jpeg";
	private int maxImageCount;

	@Before
	public void initialize() {
		long id = 0L;
		long eventId = 3L;
		byte[] photo = ImageUtils.convertToBytes(testFileTemplate.replace('?', '0'));
		int position = 0;
		this.image = new EventImage(id, eventId, photo, position);
		maxImageCount = PropertiesUtil.getMaxImageCount();
		
		this.event = new Event();
		this.event.setId(3L);
	}
	
	@Test
	@Transactional
	public void testAddImage(){
		// Test add functionality
		try {
			eventImageSrvc.addImage(image);
		} catch (ExceedingLimitException e) {
			assert(false);
		}
		
		// Max out the image collection
		Collection<EventImage> images = createMaxCollection();
		boolean popped = false; //nock it down to one since we already added one
		for(EventImage ei : images) {
			if(popped) {				
				try {
					eventImageSrvc.addImage(ei);
				} catch (ExceedingLimitException e) {
					assert(false);
				}
			}
			popped = true;
		}
		
		// Exceed the limit by one.
		try {
			eventImageSrvc.addImage(image);
			assert(false);
		} catch (ExceedingLimitException e) {
			assert(true);
		}
		
		eventImageSrvc.deleteAllImages(event.getId());
	}
	
	@Test
	@Transactional
	public void testAddImages() {
		List<EventImage> images = (List<EventImage>) this.createMaxCollection();
		
		// Test addImages
		try {
			eventImageSrvc.addImages(images);
			assert(true);
		} catch (Exception e) {
			// No exception should be thrown.
			System.out.println(e.getMessage());
			assert(false);
		}
		
		// Test consistency
		List<EventImage> retrieved = eventImageSrvc.getImagesByEvent(event);
		compareImages(images, retrieved);
		
		//Test Limit
		List<EventImage> oneImage = new ArrayList<EventImage>();
		oneImage.add(image);
		try {
			eventImageSrvc.addImages(oneImage);
			assert(false);
		}catch (ExceedingLimitException e) {
			assert(true);
		}
		
		eventImageSrvc.deleteAllImages(event.getId());
	}
	
	@Test
	@Transactional
	public void testUpdateImage() {
		// Add image
		this.image.setId(0);
		try {
			eventImageSrvc.updateImage(image);
		}catch (ExceedingLimitException e) {
			assert(false);
		}
		
		//Test image added properly
		List<EventImage> retrieved = eventImageSrvc.getImagesByEvent(event);
		assert(retrieved.size() == 1);
		compareImages(image, retrieved.get(0));
		
		// Test update Image
		image.setPhoto(getRandomImage(image.getPhoto()));
		try {
			eventImageSrvc.updateImage(image);
		} catch (ExceedingLimitException e) {
			assert(false);
		}
		retrieved = eventImageSrvc.getImagesByEvent(event);
		assert(retrieved.size() == 1);
		compareImages(image, retrieved.get(0));		
	
		// clean up
		eventImageSrvc.deleteAllImages(event.getId());	
	}
	
	/**
	 * Tests to make sure descriptions are updated correctly.
	 * @Transactional will cause update type tests to fail.
	 * 
	 * IMPORTANT! if this test fails check the integrity of the dataBase.
	 */
	@Test
	public void testUpdateImages(){
		//Update/Add images
		List <EventImage> images = (List<EventImage>) createMaxCollection();
		updateAndCompare(images);
		
		//Update all images
		changeImages(images);
		updateAndCompare(images);
		
		//Test Limit
		int randomIndex = ((int)Math.random()*maxImageCount);
		images.get(randomIndex).setId(0);
		try {
			eventImageSrvc.updateImages(images);
			assert(false);
		} catch (ExceedingLimitException e) {
			images.get(randomIndex).setId(3L);
			assert(true);
		}
		
		// clean up
		eventImageSrvc.deleteAllImages(event.getId());	
	}
	
	@Test
	@Transactional
	public void testUpdateAllImages() {
		// Update empty data.
		List<EventImage> images1 = (List<EventImage>) createMaxCollection();
		try {
		  eventImageSrvc.updateAllImages(images1);
		} catch (ExceedingLimitException e) {
			// This should not be triggered
			assert(false);
		}
		compareImages(images1, eventImageSrvc.getImagesByEvent(event));
		
		// Ensure random list is different from original.
		List<EventImage> images2 = (List<EventImage>) createMaxCollection();
		boolean sameContents = true;
		while (sameContents) {
			try {
				compareImages(images1, images2);
				images2 = (List<EventImage>) createMaxCollection();
			} catch (AssertionError ae) {
				// compare will throw an error when the lists are different.
				sameContents = false;
			}
		}
		
		// Verify that update replaces all images in the dataBase.
		try {
		  eventImageSrvc.updateAllImages(images2);
		} catch (ExceedingLimitException e) {
			// This should not be triggered
			assert(false);
		}
		compareImages(images2, eventImageSrvc.getImagesByEvent(event));
		
		// clean up
		eventImageSrvc.deleteAllImages(event.getId());	
	}

	private void compareImages(List<EventImage> images, List<EventImage> retrieved) {
		Collections.sort(images, new EventImage());
		Collections.sort(retrieved, new EventImage());
		if(retrieved.size() != images.size())
			assert(false);
		for(int index = 0; index < retrieved.size(); index++) {
			compareImages(retrieved.get(index), images.get(index));
		}
	}
	
	private void updateAndCompare(List<EventImage> images) {
		try {
			eventImageSrvc.updateImages(images);
		} catch (ExceedingLimitException e) {
			// This should not be triggered
			assert(false);
		}
		compareImages(images, eventImageSrvc.getImagesByEvent(event));
	}
	
	private void changeImages(List<EventImage> images) {
		for(EventImage ei : images) {
				ei.setPhoto(getRandomImage(ei.getPhoto()));
		}
	}
	
	private Collection<EventImage> createMaxCollection() {
		Collection<EventImage> images = new ArrayList<EventImage>();

		for(int count = 0; count < maxImageCount; count++) {
			int position = ((int)(Math.random() * 100) - 50);
			long eventId = 3L;
			byte[] photo = getRandomImage(new byte[0]);

			EventImage newImage = new EventImage(0, eventId, photo, position);
			images.add(newImage);
		}
		return images;
	}
	
	private byte[] getRandomImage(byte[] exclude){
		byte[] currentByte = new byte[0];
		do {
			Integer photoNum = (int)(Math.random() * 10);
			currentByte = ImageUtils.convertToBytes(testFileTemplate.replace("?", photoNum.toString()));
		} while(Arrays.equals(currentByte, exclude));
		return currentByte;
	}
	
	private void compareImages(EventImage ret, EventImage orig) {
		boolean eventIdEq = ret.getEventId() == orig.getEventId();
		boolean photoEq = Arrays.equals(ret.getPhoto(), orig.getPhoto());
		if(!eventIdEq || !photoEq)
			assert(false);
	}
}
