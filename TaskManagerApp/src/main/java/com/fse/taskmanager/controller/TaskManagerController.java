package com.fse.taskmanager.controller;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fse.taskmanager.model.Task;
import com.fse.taskmanager.service.TaskManagerService;

@RestController
public class TaskManagerController implements TaskManagerEndpoint {

	@Autowired
	TaskManagerService taskManagerService;

	@Override
	public ResponseEntity<Task> searchTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Task> addTask(Task task) {
		if (Objects.nonNull(task)) {
			Task newTask = taskManagerService.addTask(task);
			if (Objects.nonNull(newTask)) {
				return new ResponseEntity<>(newTask, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Task> updateTask(@RequestBody Task task) {
		if (Objects.nonNull(task)) {
			Task newTask = taskManagerService.updateTask(task);
			if (Objects.nonNull(newTask)) {
				return new ResponseEntity<>(newTask, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> delete(String id) {
		if(StringUtils.isNotBlank(id)) {
			String response = taskManagerService.deleteTask(Long.valueOf(id));
			if (StringUtils.isNotBlank(response)) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
