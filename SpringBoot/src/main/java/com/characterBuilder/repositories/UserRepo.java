package com.characterBuilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User getByEmail(String email);
    User getByEmailAndPassword(String email, String password);
}
