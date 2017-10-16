package com.characterBuilder.dbMaintenance;

import org.springframework.stereotype.Component;

import com.characterBuilder.entities.pureDBEntities.UserFlag;
import com.characterBuilder.enums.EVENT_FLAGS;
import com.characterBuilder.enums.USER_FLAGS;

@Component
public class UserFlagUpdate extends EnumUpdateAbs<USER_FLAGS, UserFlag>
{

	@Override
	protected Class<USER_FLAGS> getEnum()
	{
		return USER_FLAGS.class;
	}
}
