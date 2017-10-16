package com.characterBuilder.services.simple;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.Location;
import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.Coordinate;
import com.characterBuilder.srvc.interfaces.LocationSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.util.properties.CharBuildProp;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationSrvcTest {
	
	@Autowired
	LocationSrvc locationSrvc;
	

	@Autowired
	CharBuildProp property;

	private User user1;
	private User user2;
	
	@Before
	public void intialize() {
	  user1 = new User();
	  user1.setId(1L);
	  user2 = new User();
	  user2.setId(2L);
	}
	
	@Test
	public void testGet() {
	  List<Location> locations = locationSrvc.getByUser(user1);
	  Location home = locations.get(0);
	  assert(home.getName().equals("HOME"));
	  assert(home.getCoordinate().getLatitude() == -84.24352);
	  assert(home.getCoordinate().getLongitude() == 45.00321);
	  
	  Location work = locations.get(1);
	  assert(work.getName().equals("WORK"));
	  assert(work.getCoordinate().getLatitude() == 38.88832);
	  assert(work.getCoordinate().getLongitude() == -134.03245);
	}
	
	@Test
	public void testAdd() {
	  int max = property.coordinateCountMax();
	  int startValue = locationSrvc.getByUser(user2).size();
	  for(int count = startValue; count < max + 1; count++){
	    assert(locationSrvc.getByUser(user2).size() == count);
	    try {
	      locationSrvc.addLocation(new Location(0, user2.getId(), new Coordinate(0, generateLongitude(), generateLatitude()),
	    		  "location" + count, (int)Math.random()*40000000));
	      if(count + startValue == max)
	        fail("Limit Test Failed");
	    } catch (ExceedingLimitException e) {
	      if(count != max)
	        fail("Limit Exception Thrown Prematurely");
	    }
	  }
	  
	  testDelete();
	}
	
	public void testDelete() {
	  int existing = locationSrvc.getByUser(user2).size();
	  assert(existing > 0);
	  locationSrvc.removeAllByUser(user2);
	  existing = locationSrvc.getByUser(user2).size();
	  assert(existing == 0);
	}
	
	private double generateLongitude() {
	  return Math.random()*360 - 180;
	}
	
	private double generateLatitude() {
	  return Math.random()*180 - 90;
	}

}
