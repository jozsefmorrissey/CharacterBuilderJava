package com.characterBuilder.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.characterBuilder.entities.pureDBEntities.Permission;
import com.characterBuilder.repositories.PermissionRepo;
import com.characterBuilder.services.interfaces.PermissionSrvc;

@Service
public class PermissionSrvcImpl implements PermissionSrvc {

	@Autowired
	PermissionRepo permissionRepo;
	
	@Override
	public List<Permission> getAllPermissions() {
		return permissionRepo.findAll();
	}

}
