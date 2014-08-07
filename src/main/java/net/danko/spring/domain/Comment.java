package net.danko.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Comments")
public class Comment {
	
	@Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue
    private Integer comment_id;
	
	public Integer getComment_id() {
    	return comment_id;
    }
	
	public void setComment_id(Integer id) {
    	this.comment_id = id;
    }
	
	@Column(name = "DESCRIPTION")
    private String description;
    
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String parameter) {
    	this.description = parameter;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TASK_ID", nullable = false)
    private Task task;
    
    public Task getTask() {
		return this.task;
	}
 
	public void setTask(Task task) {
		this.task = task;
	}

}
