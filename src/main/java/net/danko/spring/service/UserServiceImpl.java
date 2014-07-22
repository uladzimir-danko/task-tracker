package net.danko.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import net.danko.spring.dao.UserDAO;
import net.danko.spring.domain.User;
 
@Service
public class UserServiceImpl implements UserService {
 
    @Autowired
    private UserDAO userDAO;
 
    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }
 
    @Transactional
    public List<User> listUser() {
 
        return userDAO.listUser();
    }
 
    @Transactional
    public void removeUser(String username) {
        userDAO.removeUser(username);
    }
    
    @Transactional
    public User getUserByName(String name) {
    	return userDAO.getUserByName(name);
    }
    
    @Transactional
    public int getUserCount() {
    	
    	return userDAO.getUserCount();
    }
    
    @Transactional
    public List<User> getAllUsers(int jtStartIndex, int jtPageSize) {
    	
    	return userDAO.getAllUsers(jtStartIndex, jtPageSize);
    }
}
