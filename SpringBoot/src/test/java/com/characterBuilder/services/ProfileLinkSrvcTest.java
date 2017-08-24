package com.characterBuilder.services;

import static org.junit.Assert.fail;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.ProfileLink;
import com.characterBuilder.services.interfaces.ProfileLinkSrvc;
import com.characterBuilder.services.interfaces.UserSrvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileLinkSrvcTest {
	
	@Autowired
	private ProfileLinkSrvc proLinkSrvc;
	
	@Autowired
	private UserSrvc userSrvc;
	
	private User user1;
	private User user2;
	private User creat;
	private ProfileLink profileLink = new ProfileLink();
	private ProfileLink selfLink = new ProfileLink();
	private ProfileLink redundantLink = new ProfileLink();
	private ProfileLink reverseRedundantLink = new ProfileLink();

	private long u1Id = 5;
	private long u2Id = 7;
	private long cId = 1;
	private boolean good = true;
	private String reason = "its the illuminati";

	
	@Before
	public void initialize() {
		user1 = userSrvc.getById(u1Id);
		user2 = userSrvc.getById(u2Id);
		creat = userSrvc.getById(cId);
		profileLink = new ProfileLink(0, user1, user2, creat, good, reason);
		selfLink = new ProfileLink(0, user1, user1, creat, good, reason);

		User user3 = userSrvc.getById(1);
		User user4 = userSrvc.getById(4);
		redundantLink = new ProfileLink(0, user3, user4, user4, good, reason);
		reverseRedundantLink = new ProfileLink(0, user4, user3, user4, good, reason);

	}
	
	@Test
	public void testGetAll() {
		List<ProfileLink> pl = proLinkSrvc.getAll(creat);
		
		boolean found = false;
		for(ProfileLink p : pl) {
			if(p.getId() == 1) {
				verifyEqual(p, 4, 4, false, "Contributer = 4");
				found = !found;
			}
			if(p.getId() == 2) {
				verifyEqual(p, 15, 9, true, "Contributer = 9");
				found = !found;
			}
			assert(found);
			found = !found;
		}
	}
	
	@Test
	public void testGetByCreator() {
		User creator = new User();
		creator.setId(608);
		List<ProfileLink> pl = proLinkSrvc.getAllByCreator(creator);
		
		boolean found = false;
		for(ProfileLink p : pl) {
			if(p.getId() == 6) {
				verifyEqual(p, 641, 608, false, "velit eu sem. Pellentesque ut ipsum ac mi eleifend");
				found = !found;
			}
			if(p.getId() == 50) {
				verifyEqual(p, 601, 608, true, "sodales elit erat vitae risus. Duis a mi fringilla mi lacinia mattis. Integer eu");
				found = !found;
			}
			assert(found);
			found = !found;
		}
	}
	
	@Test
	public void addTest() {
		proLinkSrvc.addLink(profileLink);
		List<ProfileLink> pl = proLinkSrvc.getAll(user1);
		assert(pl.size() == 1);
		verifyEqual(pl.get(0), u2Id, cId, good, reason);
		
		proLinkSrvc.removeLink(profileLink);
		pl = proLinkSrvc.getAll(user1);
		assert(pl.size() == 0);
		
		// Test composite key constraint.
		try {
			proLinkSrvc.addLink(redundantLink);
			fail("This is like should violate the primary key constraint");
		} catch (DataIntegrityViolationException e) {
			// This constraint restricts individual people form linking profiles
			// multiple times.
			assert (true);
		}
		
		// Test setters and constructors in ProfileLink should ensure userId1 < userId2
		// Which will prevent event a single link duplication.
		try {
			proLinkSrvc.addLink(reverseRedundantLink);
			fail("This is like should violate the primary key constraint");
		} catch (DataIntegrityViolationException e) {
			// This constraint restricts individual people form linking profiles
			// multiple times.
			assert (true);
		}
		
		// Test user1 != user2 constraint
		try {
			proLinkSrvc.addLink(selfLink);
			fail("This is like should violate a constraint");
		} catch (DataIntegrityViolationException e) {
			// There is no reason to link a profile to itself
			assert (true);
		}
	}
	
	private void verifyEqual(ProfileLink p, long uId, long cId, boolean good, String reason) {
		assert(p.getUser1().getId() == uId || p.getUser2().getId() == uId);
		assert(p.getCreator().getId() == cId);
		assert(p.getIsGood() == good);
		assert(p.getReason().equals(reason));
	}
}
