package net.danko.spring.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.danko.spring.domain.Comment;
import net.danko.spring.domain.CommentJsonObject;
import net.danko.spring.domain.Task;
import net.danko.spring.service.CommentService;
import net.danko.spring.service.TaskService;
import net.danko.spring.service.UserRoleService;

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
public class CommentsController {
	
	@Autowired
    private CommentService commentService;
	
	@Autowired
    private TaskService taskService;
	
	@Autowired
    private UserRoleService userRoleService;
	
	@RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ModelAndView showComments(@RequestParam("task_id") String task_id,
    		ModelAndView model) {
		
		List<String> userList = userRoleService.listDeveloper();
		List<String> statusList = new ArrayList<String>();
		statusList.add("active");
		statusList.add("complete");
		
		model.addObject("userList", userList);
		model.addObject("statusList", statusList);	
		model.addObject("currentTask", taskService.getTaskById(Integer.parseInt(task_id)));
		model.addObject("task", new Task());
		model.addObject("task_id", task_id);
        model.setViewName("comments");

        return model;
    }
	
	@RequestMapping("/springCommentsPaginationDataTables/{task_id}")
	    public @ResponseBody String springCommentPaginationDataTables(
	    		@PathVariable("task_id") String task_id) 
	        throws IOException {
		
		List<Comment> commentsList = commentService.listComment(Integer.parseInt(task_id));

        CommentJsonObject commentJsonObject = new CommentJsonObject();
        commentJsonObject.setiTotalDisplayRecords(commentsList.size());
        commentJsonObject.setiTotalRecords(commentsList.size());
        commentJsonObject.setAaData(commentsList);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json2 = gson.toJson(commentJsonObject);

        return json2;
    }
	
	@RequestMapping(value = "/addComment/{task_id}", method = RequestMethod.GET)
    public ModelAndView addComment(@PathVariable("task_id") String task_id) {
		
		ModelAndView model = new ModelAndView();
		model.addObject("task_id", task_id);
		model.addObject("comment", new Comment());
        model.setViewName("addComment");

        return model;
    }
	
	@RequestMapping(value = "/addComment/{task_id}", method = RequestMethod.POST)
    public ModelAndView saveComment(@ModelAttribute("comment") Comment comment,
            BindingResult result,
            @PathVariable("task_id") String task_id) throws UnsupportedEncodingException {
		
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			
            model.setViewName("registration");            
        } else {        	
        	
        	Task task = taskService.getTaskById(
        			Integer.parseInt(task_id));
        	
        	comment.setTask(task);  
        	commentService.addComment(comment);
        	
        	model.setViewName("redirect:/comments?task_id=" + task_id);            
        }

        return model;
    }
	
	@RequestMapping("/deleteComment/{comment_id}")
    public String deleteContact(@PathVariable("comment_id") String comment_id) {

		Comment comment = commentService.getCommentById(Integer.parseInt(comment_id));
		String url = "redirect:/comments?task_id=" + comment.getTask().getTask_id();
        commentService.removeComment(Integer.parseInt(comment_id)); 

        return url;
    }
	
	@RequestMapping(value = "/editComment/{comment_id}", method = RequestMethod.GET)
    public ModelAndView editeComment(@PathVariable("comment_id") String comment_id) {
		
		ModelAndView model = new ModelAndView();
		model.addObject("comment", commentService
				.getCommentById(Integer.parseInt(comment_id)));
        model.setViewName("editComment");

        return model;
    }
	
	@RequestMapping(value = "/editComment/{comment_id}", method = RequestMethod.POST)
    public ModelAndView saveEditComment(@ModelAttribute("comment") Comment comment,
            BindingResult result, @PathVariable("comment_id") String comment_id) 
            		throws UnsupportedEncodingException {
		
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			
            model.setViewName("registration");            
        } else {        	
        	
        	Comment commentBufer = commentService
        			.getCommentById(Integer.parseInt(comment_id));
        	commentBufer.setDescription(comment.getDescription()); 
        	commentService.updateComment(commentBufer);
        	
        	model.setViewName("redirect:/comments?task_id=" 
        			+ commentBufer.getTask().getTask_id());            
        }

        return model;
    }

}
