package com.me.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.student.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{
	
}
