package com.spring.data.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;
import com.spring.data.StudentRepository;
import com.spring.entity.Student;

@Component
public class StudentRepositoryImpl implements StudentRepository {
	private JdbcOperations jdbcOperations;

	public StudentRepositoryImpl(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	public static final String ADD_STUDENT = "insert into user values(?,?)";

	public void add(Student student) {
		jdbcOperations.update(ADD_STUDENT, student.getName(), student.getPassword());
		System.out.println("添加完成");
	}

	public static final String UPDATE_STUDENT = "update user set password=? where user=?";

	public void update(Student student) {
		jdbcOperations.update(UPDATE_STUDENT, student.getPassword(), student.getName());
		System.out.println("更新完成");
	}

	public static final String QUERY_STUDENT = "select * from user where user = ?";

	public Student query(String name) {
		return jdbcOperations.queryForObject(QUERY_STUDENT, this::MapStudent, name);
	}

	private Student MapStudent(ResultSet rs, int row) throws SQLException {
		Student student = new Student();
		student.setName(rs.getString("user"));
		student.setPassword(rs.getString("password"));
		return student;
	}
}
