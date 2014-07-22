package net.danko.spring.dao;

import net.danko.spring.domain.UserRole;

public interface UserRoleDAO {
	
	public void addUserRole(UserRole role, String username);
	
	public void removeUserRole(String username);

}
