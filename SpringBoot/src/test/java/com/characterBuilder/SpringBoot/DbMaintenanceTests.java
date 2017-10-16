package com.characterBuilder.SpringBoot;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.characterBuilder.dbMaintenance.EventFlagSrvcTest;
import com.characterBuilder.dbMaintenance.UserFlagSrvcTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	UserFlagSrvcTest.class,
	EventFlagSrvcTest.class,
})
public class DbMaintenanceTests {

}
