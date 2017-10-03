package com.characterBuilder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.UserMessage;

public interface UserMessageRepo extends JpaRepository<UserMessage, Long>
{
	public List<UserMessage> findBySenderIdOrRecipientId(long senderId, long receiverId);
}
