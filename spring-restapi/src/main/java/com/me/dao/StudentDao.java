package com.me.dao;

import com.me.entity.Student;

public interface StudentDao {
	public Student getById(int id);
	public Student saveStudent(Student student);
}
