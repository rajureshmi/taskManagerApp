package com.fse.taskmanager.service;

import com.fse.taskmanager.model.Task;

public interface TaskManagerService {

	Task addTask(Task newTask);
	
	Task updateTask(Task task);
	
	String deleteTask(Long taskId);

}
