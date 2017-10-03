package com.characterBuilder.SpringBoot;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.characterBuilder.services.simple.CategorySrvcTest;
import com.characterBuilder.services.simple.CoordinateSrvcTest;
import com.characterBuilder.services.simple.DescriptionSrvcTest;
import com.characterBuilder.services.simple.EventDescriptionSrvcTest;
import com.characterBuilder.services.simple.EventImageSrvcTest;
import com.characterBuilder.services.simple.EventMessageSrvcTest;
import com.characterBuilder.services.simple.EventSrvcTest;
import com.characterBuilder.services.simple.EventTimeMessageTest;
import com.characterBuilder.services.simple.EventTimeSrvcTest;
import com.characterBuilder.services.simple.LocationSrvcTest;
import com.characterBuilder.services.simple.PermissionSrvcTest;
import com.characterBuilder.services.simple.ProfileLinkSrvcTest;
import com.characterBuilder.services.simple.RatingSrvcTest;
import com.characterBuilder.services.simple.SkillDescriptionSrvcTest;
import com.characterBuilder.services.simple.SkillMapSrvcTest;
import com.characterBuilder.services.simple.SkillSrvcTest;
import com.characterBuilder.services.simple.UserMessageSrvcTest;
import com.characterBuilder.services.simple.UserSrvcTest;

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
	LocationSrvcTest.class,
	CoordinateSrvcTest.class,
	ProfileLinkSrvcTest.class,
	RatingSrvcTest.class,
	CategorySrvcTest.class,
	EventSrvcTest.class,
	SkillMapSrvcTest.class,
	UserMessageSrvcTest.class,
	EventMessageSrvcTest.class,
	EventTimeMessageTest.class,
})
//@RunWith(WildcardPatternSuite.class)
//@SuiteClasses({"**/*Test.class", "!gui/**"})
public class SimpleSrvTests {

}
