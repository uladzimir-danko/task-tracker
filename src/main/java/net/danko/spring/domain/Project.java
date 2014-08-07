package net.danko.spring.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "Projects")
public class Project {
	
	@Id
    @Size(min=2, max=20)
    @Column(name = "PROJECTNAME")
    private String projectname;
    
    public String getProjectname() {
    	return projectname;
    }
    
    public void setProjectname(String parameter) {
    	this.projectname = parameter;
    }
    
    @Column(name = "DESCRIPTION")
    private String description;
    
    public String getDescription() {
    	return description;
    }
    
    public void setDescription(String parameter) {
    	this.description = parameter;
    }  
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    @Cascade(CascadeType.DELETE)
	private Set<Task> tasks = new HashSet<Task>(0);  
    
    public Set<Task> getTasks() {
    	return tasks;
    }
    
    public void setTasks(Set<Task> tasks) {
    	this.tasks = tasks;
    }

}
