package com.fse.taskmanager.dao.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fse.taskmanager.model.Task;

@Entity
@Table(name = "Task")
@JsonIgnoreProperties(value = { "startDate", "endDate" }, allowGetters = true)
public class TaskDao implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Task_Id")
	private Long id;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "Parent_Id")
	private ParentTaskDao parentTask;

	@NotBlank
	@Column(name = "Task")
	private String taskName;

	@Column(name = "Start_Date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate startDate;

	@Column(name = "End_Date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate endDate;

	@NotBlank
	@Column(name = "Priority")
	private int priority;

	@Column(name = "Status")
	private TaskStatus taskStatus;

	public TaskDao() {
		super();
	}

	public TaskDao(Long id, @NotBlank String taskName, LocalDate startDate, LocalDate endDate,
			@NotBlank int priority, TaskStatus taskStatus) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.taskStatus = taskStatus;
	}

	public TaskDao(Task task) {
		super();
		this.taskName = task.getTaskName();
		this.startDate = task.getStartDate();
		this.endDate = task.getEndDate();
		this.priority = task.getPriority();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public TaskStatus getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(TaskStatus taskStatus) {
		this.taskStatus = taskStatus;
	}

	public ParentTaskDao getParentTask() {
		return parentTask;
	}

	public void setParentTask(ParentTaskDao parentTask) {
		this.parentTask = parentTask;
	}

	
}
