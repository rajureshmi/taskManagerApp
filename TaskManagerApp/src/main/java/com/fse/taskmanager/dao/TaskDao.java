package com.fse.taskmanager.dao;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fse.taskmanager.model.Task;

@Entity
@Table(name = "Tasks")
@JsonIgnoreProperties(value = { "startDate", "endDate" }, allowGetters = true)
public class TaskDao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long parentId;

	@NotBlank
	private String taskName;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate startDate;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate endDate;

	@NotBlank
	private int priority;
	private TaskStatus taskStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
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
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public TaskDao(Task task) {
		super();
		this.taskName = task.getTaskName();
		this.startDate = task.getStartDate();
		this.endDate = task.getEndDate();
		this.priority = task.getPriority();		
	}

}
