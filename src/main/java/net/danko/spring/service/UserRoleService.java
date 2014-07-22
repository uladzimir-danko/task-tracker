package net.danko.spring.service;

import net.danko.spring.domain.UserRole;

public interface UserRoleService {
	
	public void addUserRole(UserRole userRole, String username);
	
	public void removeUserRole(String username);

}