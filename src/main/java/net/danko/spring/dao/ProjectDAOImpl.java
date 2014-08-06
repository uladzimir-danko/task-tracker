package net.danko.spring.dao;

import java.util.List;

import net.danko.spring.domain.Project;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectDAOImpl implements ProjectDAO {
	
	@Autowired
    private SessionFactory sessionFactory;

	public void addProject(Project project) {
    	
        sessionFactory.getCurrentSession().save(project);
    }

    @SuppressWarnings("unchecked")
    public List<Project> listProject() {

        return sessionFactory.getCurrentSession().createQuery("from Project")
            .list();
    }

    public void removeProject(String projectname) {
        Project project = (Project) sessionFactory.getCurrentSession().load(
                Project.class, projectname);
        if (null != project) {
            sessionFactory.getCurrentSession().delete(project);
        }

    }
    
    public Project getProjectByName(String projectname) {
    	return (Project) sessionFactory.getCurrentSession().get(Project.class, projectname);
    }

}
