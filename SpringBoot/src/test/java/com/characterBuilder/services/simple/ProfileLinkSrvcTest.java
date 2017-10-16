package com.characterBuilder.services.simple;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.ProfileLink;
import com.characterBuilder.srvc.interfaces.ProfileLinkSrvc;
import com.characterBuilder.srvc.interfaces.UserSrvc;

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
		profileLink = new ProfileLink(user1, user2, creat, good, reason);
		selfLink = new ProfileLink(user1, user1, creat, good, reason);
	}
	
	@Test
	public void testGetAll() {
		List<ProfileLink> pl = proLinkSrvc.getAll(creat);
		
		boolean found = false;
		for(ProfileLink p : pl) {
			p.getId().orderUsers();
			if(p.getUser1().getId() == 1 && p.getUser2().getId() == 4) {
			//if(p.getId() == 1) {
				verifyEqual(p, 4, 4, false, "Contributer = 4");
				found = !found;
			}
			if(p.getUser1().getId() == 1 && p.getUser2().getId() == 15) {
			//if(p.getId() == 2) {
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
			if(p.getUser1().getId() == 496 && p.getUser2().getId() == 641) {

			//if(p.getId() == 6) {
				verifyEqual(p, 641, 608, false, "velit eu sem. Pellentesque ut ipsum ac mi eleifend");
				found = !found;
			}
			if(p.getUser1().getId() == 277 && p.getUser2().getId() == 601) {

			//if(p.getId() == 50) {
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
		
		// Test user1 != user2 constraint
		try {
			proLinkSrvc.addLink(selfLink);
			fail("This is like should violate a constraint");
		} catch (Exception e) {
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
