package com.characterBuilder.srvc.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.UserMessage;
import com.characterBuilder.repo.UserMessageRepo;
import com.characterBuilder.srvc.interfaces.UserMessageSrvc;
import com.characterBuilder.srvc.interfaces.UserSrvc;
import com.characterBuilder.throwable.exceptions.ExceedingLimitException;
import com.characterBuilder.throwable.exceptions.InvalidIdException;
import com.characterBuilder.throwable.exceptions.NullOrEmptyStringException;
import com.characterBuilder.util.properties.CharBuildProp;

@Service
public class UserMessageSrvcImpl implements UserMessageSrvc
{
	@Autowired
	UserMessageRepo userMsgRepo;

	@Autowired
	UserSrvc userSrvc;

	@Autowired
	CharBuildProp charProp;
	
	@Override
	public List<UserMessage> getByUser(User user)
	{
		return getByUserId(user.getId());
	}

	private List<UserMessage> getByUserId(long userId)
	{
		return userMsgRepo.findBySenderIdOrRecipientId(userId, userId);
	}

	@Override
	public UserMessage add(UserMessage userMsg)
			throws ExceedingLimitException, NullOrEmptyStringException, InvalidIdException
	{
		return add(userMsg, LocalDateTime.now());
	}

	/**
	 * Function is used explicitly for testing purposes. All messages should be
	 * forced to set the date time equal to when they are inserted into the DB
	 * 
	 * @param userMsg
	 * @throws ExceedingLimitException
	 * @throws InvalidIdException
	 * @throws NullOrEmptyStringException
	 */
	@Override
	public UserMessage addDoNotExpose(UserMessage userMsg)
			throws ExceedingLimitException, NullOrEmptyStringException, InvalidIdException
	{
		return add(userMsg, userMsg.getDateTime());
	}

	private UserMessage add(UserMessage userMsg, LocalDateTime ldt)
			throws ExceedingLimitException, NullOrEmptyStringException, InvalidIdException
	{
		inputValidation(userMsg);

		userMsg.setDateTime(ldt);

		userMsgRepo.save(userMsg);

		deleteOldMsgs(userMsg);

		return userMsg;
	}

	private void inputValidation(UserMessage userMsg) throws NullOrEmptyStringException, 
												InvalidIdException, ExceedingLimitException
	{
		if (userMsg == null)
			throw new NullPointerException();
		if (null == userMsg.getMessage() || "".equals(userMsg.getMessage()))
			throw new NullOrEmptyStringException("Message");
		
		int maxMsgLen = charProp.messageLengthMax();
		int msgLen = userMsg.getMessage().length();
		if (msgLen > maxMsgLen)
			throw new ExceedingLimitException(msgLen, maxMsgLen, "UserMessage");
		
		try {
			userSrvc.getById(userMsg.getSenderId());
		} catch (JpaObjectRetrievalFailureException e)
		{
			throw new InvalidIdException("SenderId");
		}
		
		try {
			userSrvc.getById(userMsg.getRecipientId());
		} catch (JpaObjectRetrievalFailureException e)
		{
			throw new InvalidIdException("RecipientId");
		}
	}

	@Override
	public void delete(UserMessage userMsg)
	{
		userMsgRepo.delete(userMsg);
	}

	/**
	 * Function simply provides a barrier against overflowing the database with
	 * messages. Limits can be set arbitrarily in the charBuild.properties file.
	 * 
	 * @param userMsg
	 */
	private void deleteOldMsgs(UserMessage userMsg)
	{

		remove(userMsg.getSenderId());
		remove(userMsg.getRecipientId());
	}

	/**
	 * This function removes old messages by calculating a ratio for each side
	 * of every conversation and removes only from those who represent a
	 * substantial fraction of overall correspondence. i.e. If maxMsgCount = 100
	 * there are 5 conversations that means 10 sides to a conversation and there
	 * are 110 messages only sides of the conversation with more than 10 will
	 * have messages deleted.
	 * 
	 * @param userMsg
	 * @param maxMsgCount
	 * @param msgs
	 */
	@Override
	public void deleteOldMsgs(long userId)
	{
		User user = new User();
		user.setId(userId);
		List<UserMessage> msgs = getByUser(user);

		int maxMsgCount = charProp.messageCountMax();

		int totalCount = msgs.size();
		int count2BRmv = totalCount - maxMsgCount;

		// UserMessage compare method ensures sides of conversations will be
		// grouped together.
		Collections.shuffle(msgs);
		Collections.sort(msgs);
		int msgCount = 0; // Count the number of messages in a conversation
		UserMessage currConv = msgs.iterator().next();
		List<UserMessage> sameSideOfConv = new ArrayList<UserMessage>();

		int countDeleted = 0;
		for (UserMessage msg : msgs)
		{
			if (msg.emailCompare(currConv) == 0)
			{
				msgCount++;
			} else
			{
				int deleteCount = (int) ((double) (msgCount * count2BRmv) / (double) totalCount);

				countDeleted = removeMessages(msgCount, sameSideOfConv, countDeleted, deleteCount);
				// New conversation starts
				sameSideOfConv = new ArrayList<UserMessage>();
				currConv = msg;
				msgCount = 1;
			}
			sameSideOfConv.add(msg);
		}
		int deleteCount = (msgCount * count2BRmv) / totalCount;
		removeMessages(msgCount, sameSideOfConv, countDeleted, deleteCount);
	}

	private int removeMessages(int msgCount, List<UserMessage> pardoned, int countDeleted, int deleteCount)
	{
		int start = msgCount - deleteCount;
		if (start < msgCount)
		{
			List<UserMessage> deathRow = new ArrayList<UserMessage>();
			deathRow.addAll(pardoned.subList(msgCount - deleteCount, msgCount));
			userMsgRepo.delete(deathRow);

			countDeleted += deleteCount;
			return countDeleted;
		}
		return 0;
	}

	private void remove(long userId)
	{
		// The overFlowFactor prevents the deletions of messages on every
		// insertion. Right now it is set up to allow twice the max before
		// deletion is triggered and count is reduced to max.
		double overFlowFactor = charProp.messageOverflowFactor();
		int maxMsgCount = charProp.messageCountMax();

		int count = getByUserId(userId).size();

		if (maxMsgCount * overFlowFactor < count)
		{
			deleteOldMsgs(userId);
		}
	}
}
