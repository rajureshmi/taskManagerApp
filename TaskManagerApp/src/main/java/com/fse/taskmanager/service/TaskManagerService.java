package com.fse.taskmanager.service;

import java.util.List;

import com.fse.taskmanager.controller.SearchType;
import com.fse.taskmanager.model.Task;

public interface TaskManagerService {

	Task addTask(Task newTask);

	Task updateTask(Task task);

	String deleteTask(Long taskId);

	List<Task> searchTasks(SearchType searchType, String fromValue, String toValue);

}
