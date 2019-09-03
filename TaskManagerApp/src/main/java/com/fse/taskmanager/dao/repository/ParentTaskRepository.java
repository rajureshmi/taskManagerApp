package com.fse.taskmanager.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.taskmanager.dao.entity.ParentTaskDao;

public interface ParentTaskRepository extends JpaRepository<ParentTaskDao, Long> {
	
	ParentTaskDao findByName(String name);
	
	Optional<ParentTaskDao> findById(Long id);
	
	List<ParentTaskDao> findByIdIn(List<Long> id);

}
