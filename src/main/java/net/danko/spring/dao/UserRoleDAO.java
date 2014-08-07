package net.danko.spring.dao;

import java.util.List;

import net.danko.spring.domain.User;
import net.danko.spring.domain.UserRole;

public interface UserRoleDAO {
	
	public void addUserRole(UserRole role, String username);
	
	public void updateUserRole(UserRole role, String username);
	
	public void removeUserRole(String username);
	
	public List<String> listDeveloper();
	
	public List<User> listUserDeveloper();

}
