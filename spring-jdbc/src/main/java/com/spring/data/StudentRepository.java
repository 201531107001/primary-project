package com.spring.data;

import com.spring.entity.Student;

public interface StudentRepository {
	void add(Student student);
	void update(Student student);
	Student query(String name);
}
