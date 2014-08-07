package net.danko.spring.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.danko.spring.domain.Project;
import net.danko.spring.domain.ProjectJsonObject;
import net.danko.spring.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/projects/{projectname}", method = RequestMethod.GET)
    public ModelAndView listProjects(@PathVariable("projectname") String projectname) {
		
		ModelAndView model = new ModelAndView();
		model.addObject("projectname", projectname);
        model.setViewName("redirect:/tasks");

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
	
	@RequestMapping(value = "/projects", method = RequestMethod.GET)
    public ModelAndView listProjects() {
		
		ModelAndView model = new ModelAndView();
        model.setViewName("projects");

        return model;
    }
	
	@RequestMapping(value = "newProject", method = RequestMethod.GET)
	public ModelAndView registration() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("project", new Project());
		model.setViewName("newProject");
		
		return model;		
	}
	
	@RequestMapping(value = "/newProject", method = RequestMethod.POST)
    public ModelAndView createProject(@ModelAttribute("project") Project project,
            BindingResult result) throws UnsupportedEncodingException {
		
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			
            model.setViewName("registration");            
        } else {        	
        	
        	projectService.addProject(project);
        	
        	model.setViewName("redirect:/projects");            
        }

        return model;
    }

}
