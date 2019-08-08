package com.fse.taskmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentTaskRepository extends JpaRepository<ParentTaskDao, Long> {
	
	ParentTaskDao findByParentTaskName(String parentTaskName);

}
