package com.fse.taskmanager.server.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.fse"})
public class TaskManagerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskManagerAppApplication.class, args);
	}

}
