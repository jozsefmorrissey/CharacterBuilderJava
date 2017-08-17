package com.characterBuilder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.characterBuilder.entities.pureDBEntities.Permission;

public interface PermissionRepo extends JpaRepository<Permission, Long> {

}
