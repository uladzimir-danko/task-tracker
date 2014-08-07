package net.danko.spring.dao;

import java.util.List;

import net.danko.spring.domain.Comment;

public interface CommentDAO {
	
	public void addComment(Comment comment);

    public List<Comment> listComment(int task_id);

    public void removeComment(int comment_id);
    
    public Comment getCommentById(int comment_id);
    
    public void updateComment(Comment comment);

}
