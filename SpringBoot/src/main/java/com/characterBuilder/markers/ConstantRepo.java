package com.characterBuilder.markers;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.abs.ConstantAbs;

public interface ConstantRepo<T extends ConstantAbs> extends JpaRepository<T, Long>
{
	public T getByValue(String value);
}
