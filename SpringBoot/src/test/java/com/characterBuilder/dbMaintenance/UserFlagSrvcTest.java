package com.characterBuilder.dbMaintenance;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.pureDBEntities.UserFlag;
import com.characterBuilder.enums.EVENT_FLAGS;
import com.characterBuilder.enums.USER_FLAGS;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserFlagSrvcTest extends UpdateConstantsTest<UserFlag, USER_FLAGS>
{
	@Override
	protected Class<USER_FLAGS> getEnum()
	{
		return USER_FLAGS.class;
	}
}
