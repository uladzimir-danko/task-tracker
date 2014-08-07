package net.danko.spring.dao;

import java.util.List;

import net.danko.spring.domain.User;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user) {
    	
        sessionFactory.getCurrentSession().save(user);
    }
    
public void updateUser(User user) {
    	
        sessionFactory.getCurrentSession().update(user);
    }

    @SuppressWarnings("unchecked")
    public List<User> listUser() {

        return sessionFactory.getCurrentSession().createQuery("from User")
            .list();
    }

    public void removeUser(String username) {
        User user = (User) sessionFactory.getCurrentSession().load(
                User.class, username);
        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }

    }
    
    public User getUserByName(String name) {
    	return (User) sessionFactory.getCurrentSession().get(User.class, name);
    }
    
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers(int jtStartIndex, int jtPageSize) {
    	
    	return sessionFactory.getCurrentSession()
    			.createQuery("from User").list().subList(jtStartIndex, jtStartIndex + jtPageSize);
    }
    
    public int getUserCount() {
    	
    	return sessionFactory.getCurrentSession().createQuery("from User")
                .list().size();
    }
}
