package net.danko.spring.dao;

import java.util.List;
import net.danko.spring.domain.Task;

public interface TaskDAO {
	
	public void addTask(Task task);

    public List<Task> listTask(String projectname);
    
    public Task getTaskById(int task_id);
    
    public Task getTaskByComment(int comment_id);
    
    public void updateTask(Task task);
    
    public void deleteDeveloper(String username);

}
