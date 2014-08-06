package net.danko.spring.dao;

import java.util.List;
import net.danko.spring.domain.Project;

public interface ProjectDAO {
	
	public void addProject(Project project);

    public List<Project> listProject();

    public void removeProject(String projectname);
    
    public Project getProjectByName(String projectname);

}
