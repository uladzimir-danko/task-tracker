package net.danko.spring.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.danko.spring.domain.Project;
import net.danko.spring.domain.Task;
import net.danko.spring.domain.TaskJsonObject;
import net.danko.spring.service.ProjectService;
import net.danko.spring.service.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
public class TasksController {
	
	@Autowired
    private TaskService taskService;
	
	@Autowired
    private ProjectService projectService;
	
	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ModelAndView showTasks(@RequestParam("projectname") String projectname,
    		ModelAndView model) {
		
		model.addObject("projectname", projectname);
        model.setViewName("tasks");

        return model;
    }	
	
	@RequestMapping("/springTaskPaginationDataTables/{projectname}")
	    public @ResponseBody String springTaskPaginationDataTables(
	    		@PathVariable("projectname") String projectname) 
	        throws IOException {
		
		List<Task> tasksList = taskService.listTask(projectname);

        TaskJsonObject taskJsonObject = new TaskJsonObject();
        taskJsonObject.setiTotalDisplayRecords(tasksList.size());
        taskJsonObject.setiTotalRecords(tasksList.size());
        taskJsonObject.setAaData(tasksList);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(taskJsonObject);

        return json2;
    }
	
	@RequestMapping(value = "/tasks/{task_id}", method = RequestMethod.GET)
    public ModelAndView listProjects(@PathVariable("task_id") String task_id) {
		
		ModelAndView model = new ModelAndView();
		model.addObject("task_id", task_id);
        model.setViewName("redirect:/comments");

        return model;
    }
	
	@RequestMapping(value = "/addTask/{projectname}", method = RequestMethod.GET)
    public ModelAndView addTask(@PathVariable("projectname") String projectname) {
		
		ModelAndView model = new ModelAndView();
		model.addObject("projectname", projectname);
		model.addObject("task", new Task());
        model.setViewName("addTask");

        return model;
    }
	
	@RequestMapping(value = "/addTask/{projectname}", method = RequestMethod.POST)
    public ModelAndView saveComment(@ModelAttribute("projectname") Task task,
            BindingResult result,
            @PathVariable("projectname") String projectname) throws UnsupportedEncodingException {
		
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			
            model.setViewName("registration");            
        } else {        	
        	
        	Project project = projectService.getProjectByName(projectname);
        	
        	task.setProject(project);  
        	task.setStatus("Active");
        	task.setUsername("Unkown");
        	taskService.addTask(task);
        	
        	model.setViewName("redirect:/tasks?projectname=" + projectname);            
        }

        return model;
    }
	
	@RequestMapping(value = "/editTask/{task_id}", method = RequestMethod.POST)
    public ModelAndView saveEditComment(@ModelAttribute("task") Task task,
            BindingResult result,
            @PathVariable("task_id") String task_id) throws UnsupportedEncodingException {
		
		Task taskBuffer = taskService.getTaskById(Integer.parseInt(task_id));
		ModelAndView model = new ModelAndView();
		if (task.getStatus() != null) {
			
			taskBuffer.setStatus(task.getStatus());            
        }       
		
		if (task.getUsername() != null) {
			
			taskBuffer.setUsername(task.getUsername());
		}        	 
        	taskService.updateTask(taskBuffer);
        	
        	model.setViewName("redirect:/comments?task_id=" + task_id);       

        return model;
    }

}
