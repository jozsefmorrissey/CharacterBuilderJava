package com.characterBuilder.SpringBoot;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	SimpleSrvTests.class,
	PopulatedDataSrvTests.class,
	SimpleClassTests.class,
})
public class AllTests {

}
