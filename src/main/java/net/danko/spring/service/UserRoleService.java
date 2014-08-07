package net.danko.spring.service;

import java.util.List;

import net.danko.spring.domain.User;
import net.danko.spring.domain.UserRole;

public interface UserRoleService {
	
	public void addUserRole(UserRole userRole, String username);
	
	public void updateUserRole(UserRole userRole, String username);
	
	public void removeUserRole(String username);
	
	public List<String> listDeveloper();

	public List<User> listUserDeveloper();

}