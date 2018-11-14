package com.me.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.me.entity.Student;

import utils.TimeUtils;

@Repository
public class StudentDaoImpl{
	
	@Autowired
	JdbcOperations jdbcOperations;
	
	public Student getById(int id) {
		String sql = "select * from student where id = ?";
		return jdbcOperations.query(sql, new Object[] {id}, new ResultSetExtractor<Student>() {

			public Student extractData(ResultSet rSet) throws SQLException, DataAccessException {
				try {
					return new Student(rSet.getInt(1), rSet.getString(2), rSet.getInt(3), TimeUtils.getNormalYMDTime(rSet.getDate(4).getTime()));
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
			
		});
	}

	public Student save(Student student) {
		Integer id = student.getId();
		if (id == null) {
			id = insertSpitterAndReturnId(student);
			student.setId(id);
			return student;
		} else {
			jdbcOperations.update("update student set name=?, age=?, birth=?",					
					student.getName(),
					student.getAge(),
					student.getBirth(),
					id);
		}
		return student;
	}
	
	private int insertSpitterAndReturnId(Student student) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert((JdbcTemplate)jdbcOperations).withTableName("student");
		jdbcInsert.setGeneratedKeyName("id");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("name", student.getName());
		args.put("age", student.getAge());
		args.put("birth", student.getBirth());
		int id = (int) jdbcInsert.executeAndReturnKey(args).longValue();
		return id;
	}

}
