package com.me.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
}
