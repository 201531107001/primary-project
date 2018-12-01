package com.me.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.me.entity.Student;

@Repository
public class StudentRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Student> getAllStudent() {
		String sql = "select * from student";
		return jdbcTemplate.query(sql, this::studentRowMapper);
	}

	public Student getStudentByName(String username) {
		String sql = "select * from student where username = ?";
		return jdbcTemplate.queryForObject(sql, new Object[] {username}, this::studentRowMapper);
	}
	
	private Student studentRowMapper(ResultSet rs, int rowNum) {
		try {
			return new Student(rs.getInt("uid"), rs.getString("username"), rs.getString("password"), rs.getInt("age"), rs.getInt("sex"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
