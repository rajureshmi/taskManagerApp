package com.fse.taskmanager.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ParentTask")
public class ParentTaskDao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long parentTaskName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentTaskName() {
		return parentTaskName;
	}

	public void setParentTaskName(Long parentTaskName) {
		this.parentTaskName = parentTaskName;
	}
	
	
}
