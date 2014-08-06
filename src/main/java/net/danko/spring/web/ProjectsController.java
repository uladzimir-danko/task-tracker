package net.danko.spring.web;

import java.io.IOException;
import java.util.List;

import net.danko.spring.domain.Project;
import net.danko.spring.domain.ProjectJsonObject;
import net.danko.spring.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class ProjectsController {
	
	@Autowired
    private ProjectService projectService;
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
    public ModelAndView listProjects() {
		
		ModelAndView model = new ModelAndView();
        model.setViewName("projects");

        return model;
    }	
	
	@RequestMapping(value = "/springProjectPaginationDataTables", method = RequestMethod.GET, produces = 
	        "application/json")
	    public @ResponseBody String springProjectPaginationDataTables() 
	        throws IOException {
		
		List<Project> projectsList = projectService.listProject();

        ProjectJsonObject projectJsonObject = new ProjectJsonObject();
        projectJsonObject.setiTotalDisplayRecords(projectsList.size());
        projectJsonObject.setiTotalRecords(projectsList.size());
        projectJsonObject.setAaData(projectsList);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(projectJsonObject);

        return json2;
    }

}