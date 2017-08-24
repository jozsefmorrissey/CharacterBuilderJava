package com.characterBuilder.SpringBoot;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.characterBuilder.services.CoordinateSrvcTest;
import com.characterBuilder.services.DescriptionSrvcTest;
import com.characterBuilder.services.EventDescriptionSrvcTest;
import com.characterBuilder.services.EventImageSrvcTest;
import com.characterBuilder.services.EventTimeSrvcTest;
import com.characterBuilder.services.LocationSrvcTest;
import com.characterBuilder.services.ParticipantSrvcTest;
import com.characterBuilder.services.PermissionSrvcTest;
import com.characterBuilder.services.ProfileLinkSrvcTest;
import com.characterBuilder.services.RatingSrvcTest;
import com.characterBuilder.services.SkillDescriptionSrvcTest;
import com.characterBuilder.services.SkillSrvcTest;
import com.characterBuilder.services.UserSrvcTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	ApplicationTests.class, 
	DescriptionSrvcTest.class,
	EventDescriptionSrvcTest.class,
	UserSrvcTest.class,
	EventImageSrvcTest.class,
	EventTimeSrvcTest.class,
	CoordinateSrvcTest.class,
	PermissionSrvcTest.class,
	SkillDescriptionSrvcTest.class,
	SkillSrvcTest.class,
	ParticipantSrvcTest.class,
	LocationSrvcTest.class,
	CoordinateSrvcTest.class,
	ProfileLinkSrvcTest.class,
	RatingSrvcTest.class
})
public class AllTests {

}
