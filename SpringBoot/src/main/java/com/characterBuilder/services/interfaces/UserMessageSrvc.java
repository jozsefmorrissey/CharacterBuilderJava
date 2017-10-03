package com.characterBuilder.services.interfaces;

import java.util.List;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.UserMessage;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.throwable.exceptions.InvalidIdException;
import com.characterBuilder.throwable.exceptions.NullOrEmptyStringException;

public interface UserMessageSrvc
{
	public List<UserMessage> getByUser(User user);
	
	/**
	 * This function should adjust the date time to reperesent the
	 * Approximate time of entry into the database.
	 * 
	 * @param userMsg
	 * @return
	 * @throws ExceedingLimitException
	 */
	public UserMessage add(UserMessage userMsg) throws ExceedingLimitException, 
										NullOrEmptyStringException, InvalidIdException;

	/**
	 * Function is used explicitly for testing purposes. All messages should be 
	 * forced to set the date time equal to when they are inserted into the DB. 
	 * This method should use the dateTime field in userMsg.
	 * 
	 * @param userMsg
	 * @throws ExceedingLimitException
	 * @throws InvalidIdException 
	 * @throws NullOrEmptyStringException 
	 */
	public UserMessage addDoNotExpose(UserMessage userMsg) throws ExceedingLimitException, 
												NullOrEmptyStringException, InvalidIdException;

	public void delete(UserMessage userMsg);
	public void deleteOldMsgs(long userId);
}
