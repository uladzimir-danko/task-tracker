package net.danko.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
import net.danko.spring.dao.ProjectDAO;
import net.danko.spring.domain.Project;
 
@Service
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
    private ProjectDAO projectDAO;
 
    @Transactional
    public void addProject(Project project) {
        projectDAO.addProject(project);
    }
 
    @Transactional
    public List<Project> listProject() {
 
        return projectDAO.listProject();
    }
 
    @Transactional
    public void removeProject(String projectname) {
        projectDAO.removeProject(projectname);
    }
    
    @Transactional
    public Project getProjectByName(String projectname) {
    	return projectDAO.getProjectByName(projectname);
    }
}
