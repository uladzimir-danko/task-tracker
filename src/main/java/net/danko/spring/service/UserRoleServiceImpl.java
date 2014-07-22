package net.danko.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import net.danko.spring.dao.UserRoleDAO;
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
    public void removeUserRole(String username) {
    	
        userroleDAO.removeUserRole(username);
    }
}