package net.danko.spring.dao;

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
	
	public void removeUserRole(String username) {	
		
		 Query query = session.getCurrentSession().createQuery(
				 "DELETE FROM UserRole WHERE user.username = :username");
		   
		  // Delete all
		  query.setParameter("username", username);
		  query.executeUpdate();
    }

}
