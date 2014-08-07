package net.danko.spring.dao;

import java.util.List;

import net.danko.spring.domain.Comment;
import net.danko.spring.domain.Task;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskDAOImpl implements TaskDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	public void addTask(Task task) {
    	
        sessionFactory.getCurrentSession().save(task);
    }

    @SuppressWarnings("unchecked")
    public List<Task> listTask(String projectname) {   	

        return sessionFactory.getCurrentSession().createQuery(
        		"from Task where projectname = :project")
        		.setParameter("project", projectname).list();
    }
    
    public Task getTaskById(int task_id) {
    	
    	return (Task) sessionFactory.getCurrentSession().get(Task.class, task_id);
    }
    
    public Task getTaskByComment(int comment_id) {
    	
    	return (Task) sessionFactory.getCurrentSession().createQuery(
    					"FROM Task as task left join task.comment :comment")
    					.setParameter("comment", sessionFactory.getCurrentSession()
    							.get(Comment.class, comment_id));
    }

}
