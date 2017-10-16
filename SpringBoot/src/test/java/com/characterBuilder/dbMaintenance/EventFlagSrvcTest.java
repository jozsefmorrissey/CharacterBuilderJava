package com.characterBuilder.dbMaintenance;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.pureDBEntities.EventFlag;
import com.characterBuilder.enums.EVENT_FLAGS;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventFlagSrvcTest extends UpdateConstantsTest<EventFlag, EVENT_FLAGS>
{
	@Override
	protected Class<EVENT_FLAGS> getEnum()
	{
		return EVENT_FLAGS.class;
	}
}
