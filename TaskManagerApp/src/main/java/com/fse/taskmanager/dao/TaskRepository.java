package com.fse.taskmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskDao, Long> {

}
