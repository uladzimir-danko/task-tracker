package net.danko.spring.service;

import java.util.List;
import net.danko.spring.domain.Task;

public interface TaskService {
	
	public void addTask(Task task);

    public List<Task> listTask(String projectname);
    
    public Task getTaskById(int task_id);
    
    public Task getTaskByComment(int comment_id);
    
    public void updateTask(Task task);

}
