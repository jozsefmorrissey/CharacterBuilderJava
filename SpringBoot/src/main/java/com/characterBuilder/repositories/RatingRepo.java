package com.characterBuilder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.UserRating;

public interface RatingRepo extends JpaRepository<UserRating, Long> {

	public List<UserRating> findByReciever(User reciever);
	public List<UserRating> findByAttributer(User attributer);
	public List<UserRating> findByAttributerAndReciever(User attributer, User reciever);
}
