package com.characterBuilder.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.User;
import com.characterBuilder.entities.pureDBEntities.Rating;

public interface RatingRepo extends JpaRepository<Rating, Long> {

	public List<Rating> findByReciever(User reciever);
	public List<Rating> findByAttributer(User attributer);
	public List<Rating> findByAttributerAndReciever(User attributer, User reciever);
}
