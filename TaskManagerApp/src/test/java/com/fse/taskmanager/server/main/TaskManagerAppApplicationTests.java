package com.fse.taskmanager.server.main;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;

import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fse.taskmanager.dao.entity.ParentTaskDao;
import com.fse.taskmanager.dao.entity.TaskDao;
import com.fse.taskmanager.dao.repository.TaskRepository;
import com.fse.taskmanager.model.Task;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = TaskManagerAppApplication.class)
@ActiveProfiles("test")
public class TaskManagerAppApplicationTests {

	@Value("${local.server.port}")
	private Integer port;
	private String baseUrl;
	private TestRestTemplate testRestTemplate;

	@Autowired
	private TaskRepository taskRepository;

	@Before
	public void setUp() throws Exception {
		baseUrl = "http://localhost:".concat(port.toString()).concat("/taskmanager/api");
		testRestTemplate = new TestRestTemplate();

		ParentTaskDao parentTask = new ParentTaskDao();
		parentTask.setId(1L);
		parentTask.setName("Parent Task 1");

		Task task = new Task(parentTask.getName(), "Task 1", LocalDate.now().minusDays(5), LocalDate.now(), 15);
		TaskDao taskDao = new TaskDao(task);
		taskRepository.save(taskDao);

	}

	@Test
	public void getAllTasksTest() throws Exception {
		ResponseEntity<List> response = testRestTemplate.getForEntity(baseUrl.toString().concat("/tasks"), List.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		List<Task> tasks = (List<Task>) response.getBody();
		assertThat(tasks.size(), greaterThanOrEqualTo(1));
	}

	@Test
	public void addTaskTest() throws Exception {
		Task task = new Task();
		task.setTaskName("Task 10");
		task.setStartDate(LocalDate.now().minusDays(5));
		task.setEndDate(LocalDate.now());
		task.setPriority(15);
		task.setParentTaskName("Parent Task 1");

		ResponseEntity<Task> response = testRestTemplate.postForEntity(baseUrl.toString().concat("/addTask"), task,
				Task.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		Task addedTask = (Task) response.getBody();
		assertThat(addedTask, not(null));
	}

	@Test
	public void updateTaskTest() throws Exception {
		Task task = new Task();
		task.setTaskName("Task 10");
		task.setStartDate(LocalDate.now().minusDays(5));
		task.setEndDate(LocalDate.now());
		task.setPriority(15);
		task.setParentTaskName("Parent Task 1");

		ResponseEntity<Task> response = testRestTemplate.exchange(baseUrl.toString().concat("/editTask"),
				HttpMethod.PUT, new HttpEntity<>(task), Task.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		Task updatedTask = (Task) response.getBody();
		assertThat(updatedTask, not(null));
	}

	@Test
	public void endTaskTest() throws Exception {

		ResponseEntity<String> response = testRestTemplate.exchange(baseUrl.toString().concat("/tasks/"),
				HttpMethod.DELETE, new HttpEntity<>(1), String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));

		String deleteResp = response.getBody();
		assertThat(deleteResp, equalTo("SUCCESS"));
	}

	@After
	public void tearDown() throws Exception {
		baseUrl = null;
		testRestTemplate = null;
	}

}
