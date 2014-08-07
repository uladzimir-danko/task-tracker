package net.danko.spring.dao;

import java.util.ArrayList;
import java.util.List;

import net.danko.spring.domain.User;
import net.danko.spring.domain.UserRole;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO {
	
	@Autowired
    private SessionFactory session;
	
	public void addUserRole(UserRole role, String username) {
		
		User existingUser = (User) session.getCurrentSession().get(User.class, username);
		role.setUser(existingUser);
        session.getCurrentSession().save(role);
    }
	
	public void updateUserRole(UserRole role, String username) {
		
		User existingUser = (User) session.getCurrentSession().get(User.class, username);
		role.setUser(existingUser);
        session.getCurrentSession().update(role);
    }
	
	public void removeUserRole(String username) {	
		
		 Query query = session.getCurrentSession().createQuery(
				 "DELETE FROM UserRole WHERE user.username = :username");
		   
		  // Delete all
		  query.setParameter("username", username);
		  query.executeUpdate();
    }
	
	@SuppressWarnings("unchecked")
	public List<String> listDeveloper() {		
		
		List<UserRole> userRoles = session.getCurrentSession().createQuery(
				"from UserRole").list();
		List<String> users = new ArrayList<String>();
		List<String> developers = new ArrayList<String>();
		
		for(UserRole role: userRoles) {
			
			users.add(role.getUser().getUsername());
		}
		
		for(String user: users) {
			
			if(users.indexOf(user) == users.lastIndexOf(user)) {
				developers.add(user);
			}
		}
		
		return developers;
	}
	
	public List<User> listUserDeveloper() {		
		
		List<String> users = listDeveloper();
		List<User> developers = new ArrayList<User>();
		
		for(String user: users) {
			
				developers.add( (User) session.getCurrentSession().get(User.class, user) );
		}
		
		return developers;
	}

}
