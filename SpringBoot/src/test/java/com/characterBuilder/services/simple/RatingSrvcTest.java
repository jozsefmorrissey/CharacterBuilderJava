package com.characterBuilder.services.simple;

import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.UserRating;
import com.characterBuilder.repo.UserRatingRepo;
import com.characterBuilder.srvc.interfaces.UserRatingSrvc;
import com.characterBuilder.srvc.interfaces.UserSrvc;
import com.characterBuilder.throwable.exceptions.TooCloseException;
import com.characterBuilder.util.StringUtil;
import com.characterBuilder.util.properties.CharBuildProp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingSrvcTest {
	@Autowired
	private UserRatingSrvc ratingSrvc;
	
	@Autowired
	private UserRatingRepo ratingRepo;
	
	@Autowired
	private UserSrvc userSrvc;
	
	@Autowired
	CharBuildProp charProp;
	
	private User user1;
	private User user4;
	
	private User user6;
	private User user7;
	
	private UserRating rating1;
	private UserRating rating2;
	private UserRating rating3;
	
	private int daysToWait;
	
	@Before
	public void initialize() {
		user1 = userSrvc.getById(1);
		user4 = userSrvc.getById(4);
		
		user6 = userSrvc.getById(6);
		user7 = userSrvc.getById(7);

		daysToWait = charProp.daysBetweenUserRatingsMin();
		
		String tsString = "2002-09-10T14:10:10.123";
		String desc = "Joy to work with!";
		LocalDateTime ldt = StringUtil.localDateTimeConverter(tsString);
		rating1 = new UserRating(1,2,1, desc, ldt, (short)9, (short)7);
		
		tsString = "2002-10-10T14:10:10.123";
		desc = "He is a vicious beast!";
		ldt = StringUtil.localDateTimeConverter(tsString);
		rating2 = new UserRating(2,5,1,desc,ldt,(short)0,(short)9);

		tsString = "2002-11-10T14:10:10.123";
		desc = "I like chicken I like liver...";
		ldt = StringUtil.localDateTimeConverter(tsString);
		rating3 = new UserRating(3,4,1,desc,ldt,(short)9,(short)1);
	}
	
	@Test
	public void testGetByReciever() {
		List<UserRating> ratings = ratingSrvc.getByReciever(user1);
		assert(ratings.size() == 3);
		boolean found = false;
		for(UserRating r : ratings) {
			if(r.getId() == 1) {
				assert(rating1.equals(r));
				found = true;
			}
			if(r.getId() == 2) {
				assert(rating2.hashCode() == r.hashCode());
				found = true;
			}
			if(r.getId() == 3) {
				assert(rating3.equals(r));
				found = true;
			}
			assert(found);
		}
	}
	
	@Test
	public void testGetByAttributer() {
		List<UserRating> ratings = ratingSrvc.getByAttributer(user4);
		assert(ratings.size() == 1);
		assert(ratings.get(0).equals(rating3));
	}

	@Test
	public void testAddRating() {
		//Test no ratings exist add.
		rating1.setId(0);
		rating1.setAttributer(user6.getId());
		rating1.setReciever(user7.getId());
		try {
			ratingSrvc.addRating(rating1);
		} catch (TooCloseException e) {
			fail("No Ratings should exist");
		}
		ratingSrvc.deleteRating(rating1);
	}

	@Test
	public void testWaitedLongEnough() {
		//Test add again
		LocalDateTime now = LocalDateTime.now();
		// This should set the date just far enough in the past.
		rating1.setDateTime(now.minusDays(daysToWait));
		// Bypass the srvc time constraint logic.
		rating1.setId(0);
		ratingRepo.save(rating1);
		
		rating2.setAttributer(2);
		rating2.setReciever(1);
		
		rating2.setId(0);
		try {
			ratingSrvc.addRating(rating2);
		} catch (TooCloseException e) {
			fail("All ratings should be far enough in the past to post a new rating");
		}
		ratingSrvc.deleteRating(rating1);
		ratingSrvc.deleteRating(rating2);
	}

	@Test
	public void testTooSoon() {
		LocalDateTime now = LocalDateTime.now();
		// This as long as this function does not take a whole minute
		// to execute this should be to resent for another add.
		rating1.setDateTime(now.minusDays(daysToWait).plusMinutes(1));
		// Bypass the srvc time constraint logic.
		rating1.setId(0);
		ratingRepo.save(rating1);		
		
		rating2.setAttributer(2);
		rating2.setReciever(1);

		rating2.setId(0);
		try {
			ratingSrvc.addRating(rating2);
			fail("This should throw TooCloseException");
		} catch (TooCloseException e) {}
		//Clean up
		ratingSrvc.deleteRating(rating1);
		ratingSrvc.deleteRating(rating2);
	}
}
