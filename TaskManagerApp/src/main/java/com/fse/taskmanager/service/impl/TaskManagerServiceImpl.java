package com.fse.taskmanager.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fse.taskmanager.controller.SearchType;
import com.fse.taskmanager.dao.entity.ParentTaskDao;
import com.fse.taskmanager.dao.entity.TaskDao;
import com.fse.taskmanager.dao.entity.TaskStatus;
import com.fse.taskmanager.dao.repository.ParentTaskRepository;
import com.fse.taskmanager.dao.repository.TaskRepository;
import com.fse.taskmanager.model.Task;
import com.fse.taskmanager.service.TaskManagerService;

public class TaskManagerServiceImpl implements TaskManagerService {

	private static final String SUCCESS = "SUCCESS";
	private static final String FAILURE = "FAILURE";

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
			ParentTaskDao parentTask = parentTaskRepository.findByName(newTask.getParentTaskName());
			taskDao.setParentTask(parentTask);
		}
		TaskDao addedTaskDao = taskRepository.saveAndFlush(taskDao);
		if (Objects.nonNull(addedTaskDao)) {
			logger.info("Task suuceesfully added with ID {}", addedTaskDao.getId());
			newTask.setTaskId(addedTaskDao.getId());
			return newTask;
		}
		return null;
	}

	@Transactional
	@Override
	public Task updateTask(Task taskToBeUpdated) {
		TaskDao taskDao = taskRepository.findByTaskId(taskToBeUpdated.getTaskId());
		if (Objects.nonNull(taskDao)) {
			taskDao.setPriority(taskToBeUpdated.getPriority());
			taskDao.setStartDate(taskToBeUpdated.getStartDate());
			taskDao.setEndDate(taskToBeUpdated.getEndDate());
			taskDao.setTaskName(taskToBeUpdated.getTaskName());
			if (StringUtils.isNotBlank(taskToBeUpdated.getParentTaskName())) {
				ParentTaskDao parentTask = parentTaskRepository.findByName(taskToBeUpdated.getParentTaskName());
				taskDao.setParentTask(parentTask);
			}
			taskRepository.saveAndFlush(taskDao);
		}
		return taskToBeUpdated;
	}

	@Override
	public String deleteTask(Long taskId) {
		String response = FAILURE;
		if (taskRepository.existsById(taskId)) {
			taskRepository.deleteByTaskId(taskId);
			response = SUCCESS;
		}
		return response;
	}

	@Override
	public List<Task> searchTasks(SearchType searchType, String fromValue, String toValue) {
		List<Task> tasksList = new ArrayList<>();
		switch (searchType) {
		case TASK_NAME:
			List<TaskDao> taskDaoList = taskRepository.findByTaskName(fromValue);
			tasksList = mapTaskDaoToTask(taskDaoList);
			break;
		case PRIORITY:
			List<TaskDao> priorityTaskDaoList = taskRepository.findByPriorityBetween(Integer.valueOf(fromValue),
					Integer.valueOf(toValue));
			tasksList = mapTaskDaoToTask(priorityTaskDaoList);
			break;
		case DATES:
			List<TaskDao> durationTaskDaoList = taskRepository.findByPriorityBetween(Integer.valueOf(fromValue),
					Integer.valueOf(toValue));
			tasksList = mapTaskDaoToTask(durationTaskDaoList);
			break;
		case PARENT_TASK_NAME:
			ParentTaskDao parentTask = parentTaskRepository.findByName(fromValue);
			List<TaskDao> parentTaskList = taskRepository.findByParentTaskId(parentTask.getId());
			tasksList = mapTaskDaoToTask(parentTaskList);
			break;
		default: {
		}
		}
		return tasksList;
	}

	private List<Task> mapTaskDaoToTask(List<TaskDao> taskDaoList) {
		Map<Long, String> parentTaskMap = getParentTaskMap(taskDaoList);
		List<Task> tasksList = new ArrayList<>();
		for (TaskDao taskDao : taskDaoList) {
			Task task = new Task(taskDao);
			task.setParentTaskName(parentTaskMap.get(taskDao.getParentTask()));
			tasksList.add(task);
		}
		return tasksList;
	}

	private Map<Long, String> getParentTaskMap(List<TaskDao> taskDaoList) {
		Map<Long, String> parentTaskMap = new HashMap<>();
		if (Objects.nonNull(taskDaoList) && !taskDaoList.isEmpty()) {
			Set<ParentTaskDao> parentTasksSet = taskDaoList.stream().map(TaskDao::getParentTask)
					.collect(Collectors.toSet());
			if (Objects.nonNull(parentTasksSet) && !parentTasksSet.isEmpty()) {
				List<ParentTaskDao> parentTasks = parentTaskRepository.findByIdIn(
						new ArrayList(parentTasksSet.stream().map(ParentTaskDao::getId).collect(Collectors.toSet())));

				for (ParentTaskDao pt : parentTasks) {
					parentTaskMap.put(pt.getId(), pt.getName());
				}
			}
		}
		return parentTaskMap;
	}

}
