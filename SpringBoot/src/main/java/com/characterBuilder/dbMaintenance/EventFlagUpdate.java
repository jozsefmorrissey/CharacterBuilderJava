package com.characterBuilder.dbMaintenance;

import org.springframework.stereotype.Component;

import com.characterBuilder.entities.pureDBEntities.EventFlag;
import com.characterBuilder.enums.EVENT_FLAGS;

@Component
public class EventFlagUpdate extends EnumUpdateAbs<EVENT_FLAGS, EventFlag>
{
	@Override
	protected Class<EVENT_FLAGS> getEnum()
	{
		return EVENT_FLAGS.class;
	}
}
