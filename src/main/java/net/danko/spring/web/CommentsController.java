package net.danko.spring.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import net.danko.spring.domain.Comment;
import net.danko.spring.domain.CommentJsonObject;
import net.danko.spring.domain.Task;
import net.danko.spring.service.CommentService;
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
public class CommentsController {
	
	@Autowired
    private CommentService commentService;
	
	@Autowired
    private TaskService taskService;
	
	@RequestMapping(value = "/comments", method = RequestMethod.GET)
    public ModelAndView showComments(@RequestParam("task_id") String task_id,
    		ModelAndView model) {
		
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
        			2);
        	
        	comment.setTask(task);  
        	commentService.addComment(comment);
        	
        	model.setViewName("redirect:/comments?task_id=" 
        			+ comment.getTask().getTask_id());            
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

}
