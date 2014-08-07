package net.danko.spring.service;

import java.util.List;

import net.danko.spring.domain.Comment;

public interface CommentService {
	
	public void addComment(Comment comment);

    public List<Comment> listComment(int task_id);

    public void removeComment(int comment_id);
    
    public Comment getCommentById(int comment_id);

}
