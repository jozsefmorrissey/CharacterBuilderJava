package com.characterBuilder.services.simple;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.pureDBEntities.Permission;
import com.characterBuilder.services.interfaces.PermissionSrvc;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionSrvcTest {
	@Autowired
	PermissionSrvc permissionSrvc;
	
	@Test
	public void testGetAll(){
		List<Permission> permissions = permissionSrvc.getAllPermissions();
		assert(permissions.get(0).getPermissionLevel().equals("ADMIN"));
		assert(permissions.get(1).getPermissionLevel().equals("USER"));
	}
}
