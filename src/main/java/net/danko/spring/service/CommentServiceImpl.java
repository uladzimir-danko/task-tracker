package net.danko.spring.service;

import java.util.List;

import net.danko.spring.dao.CommentDAO;
import net.danko.spring.domain.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
    private CommentDAO commentDAO;
 
    @Transactional
    public void addComment(Comment comment) {
        commentDAO.addComment(comment);
    }
 
    @Transactional
    public List<Comment> listComment(int task_id) { 
        return commentDAO.listComment(task_id);
    }
 
    @Transactional
    public void removeComment(int comment_id) {
        commentDAO.removeComment(comment_id);
    }
    
    @Transactional
    public Comment getCommentById(int comment_id) {
    	return commentDAO.getCommentById(comment_id);
    }
    
    @Transactional
    public void updateComment(Comment comment) {
    	commentDAO.updateComment(comment);
    }

}
