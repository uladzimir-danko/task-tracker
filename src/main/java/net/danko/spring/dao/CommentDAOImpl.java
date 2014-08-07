package net.danko.spring.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.danko.spring.domain.Comment;

@Repository
public class CommentDAOImpl implements CommentDAO {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public void addComment(Comment comment) {
    	
        sessionFactory.getCurrentSession().save(comment);
    }

	@SuppressWarnings("unchecked")
    public List<Comment> listComment(int task_id) {

        return sessionFactory.getCurrentSession().createQuery(
        		"from Comment where task_id = :task")
        		.setParameter("task", Integer.toString(task_id)).list();
    }

	public void removeComment(int comment_id) {
		
        Comment comment = (Comment) sessionFactory.getCurrentSession().load(
                Comment.class, comment_id);
        if (null != comment) {
        	comment.setTask(null);
            sessionFactory.getCurrentSession().delete(comment);
        }

    }
	
	public Comment getCommentById(int comment_id) {
		
		return (Comment) sessionFactory.getCurrentSession().get(Comment.class, comment_id);
	}
	
	public void updateComment(Comment comment) {
		
		Query query = sessionFactory.getCurrentSession().createQuery("update Comment set description = :message" +
				" where comment_id = :id");
		query.setParameter("message", comment.getDescription());
		query.setParameter("id", comment.getComment_id());
		query.executeUpdate();
	}
}
