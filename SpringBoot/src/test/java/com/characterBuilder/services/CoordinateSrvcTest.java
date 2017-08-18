package com.characterBuilder.services;

import static org.junit.Assert.fail;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.pureDBEntities.Coordinate;
import com.characterBuilder.services.interfaces.CoordinateSrvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoordinateSrvcTest {
	
	@Autowired
	CoordinateSrvc coordinateSrvc;
	
	@Before
	public void intialize() {
		
	}
	
	@Test
	public void testGet() {
		Coordinate coordinate = coordinateSrvc.getById(1);
		assert(coordinate.getLatitude() == -84.24352);
		assert(coordinate.getLongitude() == 45.00321);
	}
	
	@Test
	public void testAdd() {
		Coordinate coordinate = new Coordinate(0, 34.234, 44.333);
		coordinateSrvc.addCoordinate(coordinate);
		
		Coordinate retrieved = coordinateSrvc.getById(coordinate.getId());

		assert(retrieved.getLongitude() == coordinate.getLongitude());
		assert(retrieved.getLatitude() == coordinate.getLatitude());
		
		testDelete(coordinate, retrieved);
	}
	
	public void testDelete(Coordinate coordinate, Coordinate retrieved) {
		coordinateSrvc.deleteCoordinate(retrieved);
		
		try {
			coordinateSrvc.getById(coordinate.getId());
		fail("Method should throw EntityNotFoundException");
		} catch (EntityNotFoundException e) {
			assert(true);
		}
	}
}
