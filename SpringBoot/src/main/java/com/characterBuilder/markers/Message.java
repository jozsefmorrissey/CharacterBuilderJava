package com.characterBuilder.markers;

import java.time.LocalDateTime;

public interface Message
{
	public long getSenderId();
	public long getRecipientId();
	public LocalDateTime getDateTime();
	public String getMessage();
}
