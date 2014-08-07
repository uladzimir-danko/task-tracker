package net.danko.spring.service;

import java.util.List;

import net.danko.spring.dao.TaskDAO;
import net.danko.spring.domain.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements TaskService {
	
	@Autowired
    private TaskDAO taskDAO;
 
    @Transactional
    public void addTask(Task task) {
        taskDAO.addTask(task);
    }
 
    @Transactional
    public List<Task> listTask(String projectname) {
 
        return taskDAO.listTask(projectname);
    }
    
    @Transactional
    public Task getTaskById(int task_id) {
    	
    	return taskDAO.getTaskById(task_id);
    }
    
    @Transactional
    public Task getTaskByComment(int comment_id) {
    	
    	return taskDAO.getTaskByComment(comment_id);
    }
    
    @Transactional
    public void updateTask(Task task) {
    	
    	taskDAO.updateTask(task);
    }

}
