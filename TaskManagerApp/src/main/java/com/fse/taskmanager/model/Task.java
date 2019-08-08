package com.fse.taskmanager.model;

import java.time.LocalDate;

import com.fse.taskmanager.dao.TaskDao;

public class Task {

	private long taskId;
	private String parentTaskName;
	private String taskName;
	private LocalDate startDate;
	private LocalDate endDate;
	private int priority;
	
	
	public long getTaskId() {
		return taskId;
	}


	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}


	public String getParentTaskName() {
		return parentTaskName;
	}


	public void setParentTaskName(String parentTaskName) {
		this.parentTaskName = parentTaskName;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public LocalDate getStartDate() {
		return startDate;
	}


	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}


	public LocalDate getEndDate() {
		return endDate;
	}


	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}


	public int getPriority() {
		return priority;	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


}
