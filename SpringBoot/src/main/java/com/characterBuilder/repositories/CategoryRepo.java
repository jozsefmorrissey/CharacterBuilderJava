package com.characterBuilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}
