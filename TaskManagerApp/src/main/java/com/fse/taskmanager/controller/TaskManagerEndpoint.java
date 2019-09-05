package com.fse.taskmanager.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fse.taskmanager.model.Task;

public interface TaskManagerEndpoint {

	@GetMapping(value = "/tasks")
	public ResponseEntity<List<Task>> searchTasks(SearchType searchType, String fromValue, String toValue);

	@PostMapping(value = "/addTask")
	public ResponseEntity<Task> addTask(@RequestBody Task task);

	@PutMapping(value = "/editTask")
	public ResponseEntity<Task> updateTask(@RequestBody Task task);

	@DeleteMapping(value = "/tasks/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") String id);

}
