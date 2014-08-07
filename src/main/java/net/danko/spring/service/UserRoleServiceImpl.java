package net.danko.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import net.danko.spring.dao.UserRoleDAO;
import net.danko.spring.domain.User;
import net.danko.spring.domain.UserRole;
 
@Service
public class UserRoleServiceImpl implements UserRoleService {
 
    @Autowired
    private UserRoleDAO userroleDAO;
 
    @Transactional
    public void addUserRole(UserRole userRole, String username) {
    	
        userroleDAO.addUserRole(userRole, username);
    }
    
    @Transactional
    public void updateUserRole(UserRole userRole, String username) {
    	
        userroleDAO.updateUserRole(userRole, username);
    }
 
    @Transactional
    public void removeUserRole(String username) {
    	
        userroleDAO.removeUserRole(username);
    }
    
    @Transactional
    public List<String> listDeveloper() {
    	
    	return userroleDAO.listDeveloper();
    }
    
    @Transactional
    public List<User> listUserDeveloper() {
    	
    	return userroleDAO.listUserDeveloper();
    }
}