package com.characterBuilder.srvc.interfaces;

import java.util.List;

import com.characterBuilder.entities.pureDBEntities.Permission;

public interface PermissionSrvc {
	public List<Permission> getAllPermissions();
}
