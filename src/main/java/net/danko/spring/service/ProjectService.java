package net.danko.spring.service;

import java.util.List;
import net.danko.spring.domain.Project;

public interface ProjectService {
	
	public void addProject(Project project);

    public List<Project> listProject();

    public void removeProject(String projectname);
    
    public Project getProjectByName(String name);

}

