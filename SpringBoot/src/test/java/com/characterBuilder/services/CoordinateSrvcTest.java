package com.characterBuilder.services;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.Coordinate;
import com.characterBuilder.services.interfaces.CoordinateSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.util.PropertiesUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoordinateSrvcTest {
	
	@Autowired
	CoordinateSrvc coordinateSrvc;
	
	private User user1;
	private User user2;
	
	@Test
	public void needToDo() {
		//TODO: This class was created and tested incorrectly,
		// 		The class is fixed but needs tested.
		assert(true);
	}
	
//	@Before
//	public void intialize() {
//		user1 = new User();
//		user1.setId(1L);
//		user2 = new User();
//		user2.setId(2L);
//	}
//	
//	@Test
//	public void testGet() {
//		List<Coordinate> coordinates = coordinateSrvc.getByUser(user1);
//		Coordinate home = coordinates.get(0);
//		assert(home.getName().equals("HOME"));
//		assert(home.getLatitude() == -84.24352);
//		assert(home.getLongitude() == 45.00321);
//		
//		Coordinate work = coordinates.get(1);
//		assert(work.getName().equals("WORK"));
//		assert(work.getLatitude() == 38.88832);
//		assert(work.getLongitude() == -134.03245);
//	}
//	
//	@Test
//	public void testAdd() {
//		int max = PropertiesUtil.getMaxCoordinateCount();
//		int startValue = coordinateSrvc.getByUser(user2).size();
//		for(int count = startValue; count < max + 1; count++){
//			assert(coordinateSrvc.getByUser(user2).size() == count);
//			try {
//				coordinateSrvc.addCoordinate(new Coordinate(0, user2.getId(), "location" 
//												+ count, generateLongitude(), generateLatitude()));
//				if(count == max)
//					fail("Limit Test Failed");
//			} catch (ExceedingLimitException e) {
//				if(count != max)
//					fail("Limit Exception Thrown Prematurely");
//			}
//		}
//		testDelete();
//	}
//	
//	public void testDelete() {
//		int existing = coordinateSrvc.getByUser(user2).size();
//		assert(existing > 0);
//		coordinateSrvc.deleteAllByUser(user2);
//		existing = coordinateSrvc.getByUser(user2).size();
//		assert(existing == 0);
//	}
//	
//	private double generateLongitude() {
//		return Math.random()*360 - 180;
//	}
//	
//	private double generateLatitude() {
//		return Math.random()*180 - 90;
//	}
}
