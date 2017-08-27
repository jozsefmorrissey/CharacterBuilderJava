package com.characterBuilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.UserMessage;

public interface UserMessageRepo extends JpaRepository<UserMessage, Long> {

}
