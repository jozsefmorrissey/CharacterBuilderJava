package com.characterBuilder.util.properties;

import org.springframework.stereotype.Component;

import com.characterBuilder.util.PropertiesAbs;

@Component
public class CharBuildProp extends PropertiesAbs
{
	private final static String fileName = "\\src\\main\\resources\\charBuild.properties";

	public CharBuildProp()
	{
		super(fileName);
	}

	/*
	 * The following functions just retrieve the indicated property.
	 */
	public int descriptionSegmentLength()
	{
		return getInteger("desciption.segment.length");
	}

	public int descriptionLength()
	{
		return getInteger("description.length");
	}

	public int imageCountMax()
	{
		return getInteger("image.count.max");
	}

	public int eventTimeCountMax()
	{
		return getInteger("eventTime.count.max");
	}

	public int coordinateCountMax()
	{
		return getInteger("coordinate.count.max");
	}

	public int participantMax()
	{
		return getInteger("participant.max");
	}

	public int daysBetweenUserRatingsMin()
	{
		return getInteger("daysBetweenUserRatings.min");
	}

	public int messageLengthMax()
	{
		return getInteger("message.length.max");
	}

	public int messageCountMax()
	{
		return getInteger("message.count.max");
	}

	public int messageCountConversationMax()
	{
		return getInteger("message.count.conversation.max");
	}

	public double messageOverflowFactor()
	{
		return getDouble("message.overflowFactor");
	}

	public int eventMessageLengthMax()
	{
		return getInteger("eventMessage.length.max");
	}

	public int eventMessageCountMax()
	{
		return getInteger("eventMessage.count.max");
	}

	public double eventMessageOverflowFactor()
	{
		return getDouble("eventMessage.overflowFactor");
	}

	public int eventTimeMessageLengthMax()
	{
		return getInteger("eventTimeMessage.length.max");
	}

	public int eventTimeMessageCountMax()
	{
		return getInteger("eventTimeMessage.count.max");
	}

	public double eventTimeMessageOverflowFactor()
	{
		return getDouble("eventTimeMessage.overflowFactor");
	}
}
