package com.characterBuilder.SpringBoot;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.characterBuilder.services.complex.ParticipantSrvcTest;
import com.characterBuilder.services.simple.CategorySrvcTest;
import com.characterBuilder.services.simple.CoordinateSrvcTest;
import com.characterBuilder.services.simple.DescriptionSrvcTest;
import com.characterBuilder.services.simple.EventDescriptionSrvcTest;
import com.characterBuilder.services.simple.EventImageSrvcTest;
import com.characterBuilder.services.simple.EventSrvcTest;
import com.characterBuilder.services.simple.EventTimeSrvcTest;
import com.characterBuilder.services.simple.LocationSrvcTest;
import com.characterBuilder.services.simple.PermissionSrvcTest;
import com.characterBuilder.services.simple.ProfileLinkSrvcTest;
import com.characterBuilder.services.simple.RatingSrvcTest;
import com.characterBuilder.services.simple.SkillDescriptionSrvcTest;
import com.characterBuilder.services.simple.SkillMapSrvcTest;
import com.characterBuilder.services.simple.SkillSrvcTest;
import com.characterBuilder.services.simple.UserSrvcTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	ParticipantSrvcTest.class
})
public class PopulatedDataSrvTests {

}
