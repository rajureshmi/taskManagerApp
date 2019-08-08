package com.fse.taskmanager.service.impl;

import java.util.Objects;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fse.taskmanager.dao.ParentTaskDao;
import com.fse.taskmanager.dao.ParentTaskRepository;
import com.fse.taskmanager.dao.TaskDao;
import com.fse.taskmanager.dao.TaskRepository;
import com.fse.taskmanager.dao.TaskStatus;
import com.fse.taskmanager.model.Task;
import com.fse.taskmanager.service.TaskManagerService;

public class TaskManagerServiceImpl implements TaskManagerService {

	private static final Logger logger = LoggerFactory.getLogger(TaskManagerServiceImpl.class);

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	ParentTaskRepository parentTaskRepository;

	@Transactional
	@Override
	public Task addTask(Task newTask) {
		TaskDao taskDao = new TaskDao(newTask);
		taskDao.setTaskStatus(TaskStatus.IN_PROGRESS);
		if (StringUtils.isNotBlank(newTask.getParentTaskName())) {
			ParentTaskDao parentTask = parentTaskRepository.findByParentTaskName(newTask.getParentTaskName());
			taskDao.setParentId(parentTask.getId());
		}
		TaskDao addedTaskDao = taskRepository.saveAndFlush(taskDao);
		if (Objects.nonNull(addedTaskDao)) {
			logger.info("Task suuceesfully added with ID {}", addedTaskDao.getId());
			newTask.setTaskId(addedTaskDao.getId());
			return newTask;
		}
		return null;
	}

}
