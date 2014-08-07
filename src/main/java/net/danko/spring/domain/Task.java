package net.danko.spring.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Tasks")
public class Task {
	
	@Id
    @Column(name = "TASK_ID")
    @GeneratedValue
    private Integer task_id;
	
	public Integer getTask_id() {
    	return task_id;
    }
	
	public void setTask_id(Integer id) {
    	this.task_id = id;
    }
	
	@Size(min=2, max=20)
    @Column(name = "TASKNAME")
    private String taskname;
    
    public String getTaskname() {
    	return taskname;
    }
    
    public void setTaskname(String parameter) {
    	this.taskname = parameter;
    }
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    public String getDescription() {
    	return description;
    }
    
    public void setLanguage(String parameter) {
    	this.description = parameter;
    }  
    
    @Column(name = "STATUS")
    private Boolean status;
    
    public Boolean getStatus() {
    	return status;
    }
    
    public void setStatus(Boolean parameter) {
    	this.status = parameter;
    }
    
    @Column(name = "USERNAME")
    private String username;
    
    public String getUsername() {
    	return username;
    }
    
    public void setUsername(String parameter) {
    	this.username = parameter;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECTNAME", nullable = false)
    private Project project;
    
    public Project getProject() {
		return this.project;
	}
 
	public void setProject(Project project) {
		this.project = project;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    @Cascade(CascadeType.ALL)
	private Set<Comment> comments = new HashSet<Comment>(0);  
    
    public Set<Comment> getComments() {
    	return comments;
    }
    
    public void setComments(Set<Comment> comments) {
    	this.comments = comments;
    }

}
